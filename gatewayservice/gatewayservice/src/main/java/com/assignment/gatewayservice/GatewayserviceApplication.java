package com.assignment.gatewayservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class GatewayserviceApplication {

	@Value("${redis.server.uri:localhost}")
	private String redisUri;
	
	@Value("${redis.server.port:6379}")
	private int redisPort;
	
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayserviceApplication.class, args);
	}

	@SuppressWarnings("deprecation")
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory
	      = new JedisConnectionFactory();
	    jedisConFactory.setHostName(redisUri);
	    jedisConFactory.setPort(redisPort);
	    return jedisConFactory;
	}

	@Bean
	RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
	
	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return new RestTemplate();
	}

}
