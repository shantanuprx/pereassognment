package com.assignment.notificationservice.consumer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageProcessor {

	@Value("${kafka.topic.notification-topic}")
	private String emailTopic;
	
	@KafkaListener(topics = "#{__listener.emailTopic}")
	public void process(String messageFromTopic) {
		System.out.println("Sending mail :- " + messageFromTopic);
	}
	
	public String getEmailTopic() {
		return emailTopic;
	}
}
