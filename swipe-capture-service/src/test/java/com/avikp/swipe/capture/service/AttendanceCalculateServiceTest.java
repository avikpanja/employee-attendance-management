package com.avikp.swipe.capture.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.avikp.swipe.capture.entity.EmployeeSwipeDetails;
import com.avikp.swipe.capture.entity.SwipeInfo;
import com.avikp.swipe.capture.model.AttendanceData;
import com.avikp.swipe.capture.repo.SwipeInfoRepository;

@SpringBootTest
class AttendanceCalculateServiceTest {

	@Autowired AttendanceCalculateService acService;
	@MockBean SwipeInfoRepository swipeRepository;
	
	private SwipeInfo existingSwipeInfo;
	
	@BeforeEach
	void init() throws Exception {
		List<EmployeeSwipeDetails> employeeWiseSwipeList = new ArrayList<>();
		EmployeeSwipeDetails oneEmpSwipeDetails = new EmployeeSwipeDetails();
		oneEmpSwipeDetails.setEmpId(101);
		oneEmpSwipeDetails.getInTimeList().add(LocalTime.now());
		oneEmpSwipeDetails.getOutTimeList().add(LocalTime.now());
		employeeWiseSwipeList.add(oneEmpSwipeDetails);
		
		existingSwipeInfo = new SwipeInfo(
				Integer.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))), 
				employeeWiseSwipeList);
	}
	
	@Test
	void testCalculateEmployeeWiseAttendanceForToday() {
		// when
		when(swipeRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(existingSwipeInfo));
		
		// then
		List<AttendanceData> resultList = acService.calculateEmployeeWiseAttendanceForToday();
		assertEquals(1, resultList.size());
	}

	@Test
	void testCalculateEmployeeWiseAttendanceFor_whenRecordNotPresent() {
		// when
		when(swipeRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
		
		// then
		assertThrows(RuntimeException.class, () -> acService.calculateEmployeeWiseAttendanceFor(LocalDate.of(1900, 12, 1))) ;
	}

}
