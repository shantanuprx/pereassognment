package com.assignment.gatewayservice;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/* *
 * Application starter class with 
 * Redis pool bean
 * 
 * */
@SpringBootApplication
public class GatewayserviceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayserviceApplication.class, args);
	}

	@Value("${redis.url}")
	private String redisUrl;
	
	@Value("${redis.port}")
	private int redisPort;
	
	@Bean
	JedisPool jedisPool() {
		JedisPool jedisPool = new JedisPool(buildPoolConfig(), redisUrl, redisPort);
		return jedisPool;
	}
	
	private JedisPoolConfig buildPoolConfig() {
	    final JedisPoolConfig poolConfig = new JedisPoolConfig();
	    poolConfig.setMaxTotal(128);
	    poolConfig.setMaxIdle(128);
	    poolConfig.setMinIdle(16);
	    poolConfig.setTestOnBorrow(true);
	    poolConfig.setTestOnReturn(true);
	    poolConfig.setTestWhileIdle(true);
	    poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
	    poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
	    poolConfig.setNumTestsPerEvictionRun(3);
	    poolConfig.setBlockWhenExhausted(true);
	    return poolConfig;
	}

}
