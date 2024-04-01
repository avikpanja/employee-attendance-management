package com.attendance.monitor.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.monitor.model.RequestData;
import com.attendance.monitor.model.SuccessResponse;
import com.attendance.monitor.service.AttendanceService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("attendance/")
public class AttendanceMonitorController {
	
	private static final Logger logger = LoggerFactory.getLogger(AttendanceMonitorController.class);

	@Autowired AttendanceService attendanceService; 
	
	@GetMapping("total-hour")
	public ResponseEntity<SuccessResponse> totalHourSpentInOneDay(@RequestBody RequestData data) {
		LocalDate date = LocalDate.parse(data.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
				
		Integer totalHourSpent = attendanceService.fetchTotalHoursSpentInOneDay(data.getEmployeeId(), date);
		SuccessResponse response = new SuccessResponse();
		response.setData(Map.of("totalHour", totalHourSpent));
		
		logger.info("totalHourSpentInOneDay() execution - completed");
		return ResponseEntity.ok().body(response);
	}
}
