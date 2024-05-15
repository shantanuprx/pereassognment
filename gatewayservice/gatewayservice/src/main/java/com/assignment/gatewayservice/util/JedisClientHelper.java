package com.assignment.gatewayservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

/**
 * Redis helper class to save keys on redis server
 * */
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
}
