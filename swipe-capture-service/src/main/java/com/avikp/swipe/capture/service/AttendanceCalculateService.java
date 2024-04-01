package com.avikp.swipe.capture.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avikp.swipe.capture.entity.SwipeInfo;
import com.avikp.swipe.capture.exception.RecordNotFoundException;
import com.avikp.swipe.capture.model.AttendanceData;
import com.avikp.swipe.capture.repo.SwipeInfoRepository;

@Service
public class AttendanceCalculateService {

	private static final Logger logger = LoggerFactory.getLogger(AttendanceCalculateService.class);
	
	@Autowired SwipeInfoRepository swipeInfoRepository;
	
	public List<AttendanceData> calculateEmployeeWiseAttendanceForToday() {
		return this.calculateEmployeeWiseAttendanceFor(LocalDate.now());
	}
	
	public List<AttendanceData> calculateEmployeeWiseAttendanceFor(LocalDate date) {
		int id = Integer.valueOf(date.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		SwipeInfo swipeInfo = this.swipeInfoRepository.findById(id).orElseThrow(
				() -> new RecordNotFoundException("Record not found for date " + date));
		
		logger.debug("Record present for {}" , date);
		
		List<AttendanceData> employeeWiseAttendanceList = swipeInfo.getEmpSwipeDetailsList()
				.stream()
				.map(empSwipeDetails ->
					AttendanceData.newObject(
							date, 
							empSwipeDetails.getInTimeList().get(0),
							empSwipeDetails.getOutTimeList().get(empSwipeDetails.getOutTimeList().size()-1), 
							empSwipeDetails.getEmpId()
					)
				).collect(Collectors.toList());
		
		logger.debug("Attendance count for the day - {}" , employeeWiseAttendanceList.size());
		return employeeWiseAttendanceList;
	}
}
