package com.assignment.orderservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaClientForOrderNotification {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka.topic.notification-topic}")
	private String emailTopicName;

	@Async
	public void sendMessage(String msg) {
		log.debug("Entering sendMessage method at  {}", System.currentTimeMillis());
		try {
			kafkaTemplate.send(emailTopicName, msg);
		} catch (Exception ex) {
			log.error("Exception occurred while sending data to topic ", ex);
			throw ex;
		} finally {
			log.debug("Exiting sendMessage method at  {}", System.currentTimeMillis());
		}
	}
}
