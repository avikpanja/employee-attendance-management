package com.avikp.swipe.capture.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avikp.swipe.capture.model.AttendanceData;
import com.avikp.swipe.capture.model.SuccessResponse;
import com.avikp.swipe.capture.service.AttendanceCalculateService;
import com.avikp.swipe.capture.service.KafkaPublisher;

@RestController
@RequestMapping("/publish")
public class AttendancePublishController {
	
	private static final Logger logger = LoggerFactory.getLogger(AttendancePublishController.class);
	
	@Autowired AttendanceCalculateService attendanceCalculateService;
	@Autowired KafkaPublisher kafkaPublisher;
	
	@GetMapping("/attendance")
	public ResponseEntity<SuccessResponse> calculateAndPublishAttendance() {
		List<AttendanceData> attendanceList = attendanceCalculateService.calculateEmployeeWiseAttendanceForToday();
		int count = 0; 
		for(AttendanceData oneRecord: attendanceList) {
			kafkaPublisher.publishAttendanceData(oneRecord);
			++count;
		}
		logger.debug("Total published message count - " + count);
		return ResponseEntity.ok().body(
				new SuccessResponse(HttpStatus.OK.value(), "Total " + count + " record(s) published", LocalDateTime.now()));
	}
}
