package com.assignment.orderservice;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.assignment.orderservice.security.service.AuthFilter;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/* *
 * Application starter class with 
 * Redis pool and Auth filter bean
 * Also contains configuration for kafka client
 * 
 * */
@EnableFeignClients
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}

	@Value("${redis.url}")
	private String redisUrl;
	
	@Value("${redis.port}")
	private int redisPort;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Value("${kafka.bootstrap.server}")
	private String bootstrapAddress;
	
	@Bean
	JedisPool jedisPool() {
		JedisPool jedisPool = new JedisPool(buildPoolConfig(), redisUrl, redisPort);
		return jedisPool;
	}
	
	@SuppressWarnings("deprecation")
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	FilterRegistrationBean authFilterRegistraionBean() {
		FilterRegistrationBean authFilterBean = new FilterRegistrationBean();
		authFilterBean.setFilter(applicationContext.getBean(AuthFilter.class));
		authFilterBean.addUrlPatterns("/*");
		authFilterBean.setName("authFilter");
		authFilterBean.setOrder(1);
	    return authFilterBean;
	}
	
	@Bean
    ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
          ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
          bootstrapAddress);
        configProps.put(
          ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        configProps.put(
          ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
          StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
