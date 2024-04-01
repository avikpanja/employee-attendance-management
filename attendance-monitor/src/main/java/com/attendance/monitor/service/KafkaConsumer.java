package com.attendance.monitor.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.attendance.monitor.entity.Attendance;
import com.attendance.monitor.model.AttendanceData;
import com.attendance.monitor.repo.AttendanceRepository;
import com.attendance.monitor.util.Util;

@Service
public class KafkaConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@Autowired AttendanceRepository attendanceRepository;
	
	@KafkaListener(topics = "#{'${spring.kafka.template.default-topic}'}", containerFactory = "kafkaListenerContainerFactory")
	public void consumeMessage(AttendanceData attendanceObj) {
		logger.debug("Message received at {} is {}, ", LocalDateTime.now(), attendanceObj);
		
		Attendance entityToPersist = Util.convertFromAttendanceDataToAttendance(attendanceObj);
		attendanceRepository.saveAndFlush(entityToPersist);
	}
}
