package com.avikp.swipe.capture.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.avikp.swipe.capture.model.AttendanceData;

@Service
public class KafkaPublisher {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaPublisher.class);

	@Autowired KafkaTemplate<String, Object> kafkaTemplate;
	
	@Value("${spring.kafka.template.default-topic}")
	private String topicName;
	
	public void publishAttendanceData(AttendanceData data) {
		logger.debug("Publishing message at {} is {}", LocalDateTime.now(), data);
		kafkaTemplate.send(topicName, data);
	}
}
