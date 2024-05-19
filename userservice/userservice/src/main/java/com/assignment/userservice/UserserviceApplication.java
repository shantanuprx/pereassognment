package com.assignment.userservice;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.assignment.userservice.security.service.AuthFilter;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/* *
 * Application starter class with 
 * Redis pool and Auth filter bean
 * 
 * */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Value("${redis.url}")
	private String redisUrl;
	
	@Value("${redis.port}")
	private int redisPort;
	
	@Autowired
	private ApplicationContext applicationContext;
	
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
	
	@SuppressWarnings("unchecked")
	@Bean
	FilterRegistrationBean authFilterRegistraionBean() {
		FilterRegistrationBean authFilterBean = new FilterRegistrationBean();
		authFilterBean.setFilter(applicationContext.getBean(AuthFilter.class));
		authFilterBean.addUrlPatterns("/*");
		authFilterBean.setName("authFilter");
		authFilterBean.setOrder(1);
	    return authFilterBean;
	}

}
