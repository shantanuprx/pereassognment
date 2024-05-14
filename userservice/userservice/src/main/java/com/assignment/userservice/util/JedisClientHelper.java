package com.assignment.userservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

@Slf4j
@Component
public class JedisClientHelper {

	@Autowired
	private JedisPool jedisPool;

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
			jedisIntance.expire(key, 60 * 15); //Extending by 15 mins
		}
	}
}
