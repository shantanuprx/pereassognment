package com.assignment.productservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.productservice.dto.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

/**
 * Redis helper class to save and fetch key value from redis server. Also helps
 * in extending the expiry of keys.
 */

@Slf4j
@Component
public class JedisClientHelper {

	@Autowired
	private JedisPool jedisPool;

	private static final String PROUDCT_INSTANCE_PREFIX = "PRODUCT_INSTANCE_";

	private static final String PROUDCT_ID_PREFIX = "PRODUCT_ID_";

	public void saveKeyPair(String key, String value) {
		try (Jedis jedisIntance = jedisPool.getResource()) {
			log.debug("Saving Key {} ", key);
			jedisIntance.set(key, value, new SetParams().ex(60 * 15)); // Setting session for 15 mins
		}
	}

	public String getValue(String key) {
		try (Jedis jedisIntance = jedisPool.getResource()) {
			log.debug("fetching value for Key {} ", key);
			return jedisIntance.get(key);
		}
	}

	public void extendTokenLife(String key) {
		try (Jedis jedisIntance = jedisPool.getResource()) {
			log.debug("Extending expiration for Key {} ", key);
			jedisIntance.expire(key, 60 * 15); // Extending by 15 mins
		}
	}

	/**
	 * Load the product dto string saved in the cache Returns null if key doesn't
	 * exist
	 * 
	 * @param request
	 * @return
	 */
	public String loadFromCache(ProductDto request) {
		try (Jedis jedisIntance = jedisPool.getResource()) {
			StringBuilder sb = new StringBuilder();
			sb.append(PROUDCT_INSTANCE_PREFIX);
			sb.append(request.getProductId());
			log.debug("Checking redis for Key {} ", sb.toString());
			return jedisIntance.get(sb.toString());
		}
	}

	/**
	 * Checks if same product info is fetched more than 3 times or not if it
	 * exceeds, save it in the cache
	 * 
	 * @param productDto
	 * @throws JsonProcessingException
	 */
	public void saveProductInCache(ProductDto productDto) throws JsonProcessingException {
		try (Jedis jedisIntance = jedisPool.getResource()) {
			StringBuilder hitCountKey = new StringBuilder();
			hitCountKey.append(PROUDCT_ID_PREFIX);
			hitCountKey.append(productDto.getProductId());
			String result = jedisIntance.get(hitCountKey.toString());
			if (result != null) {
				int totalHits = Integer.parseInt(result);
				if (totalHits >= 3) {
					StringBuilder tempSb = new StringBuilder();
					tempSb.append(PROUDCT_INSTANCE_PREFIX);
					tempSb.append(productDto.getProductId());
					jedisIntance.set(tempSb.toString(), new ObjectMapper().writeValueAsString(productDto),
							new SetParams().ex(60 * 60 * 24));
				} else {
					jedisIntance.set(hitCountKey.toString(), String.valueOf(++totalHits));
				}
			} else {
				jedisIntance.set(hitCountKey.toString(), String.valueOf(1));
			}
		}

	}
}
