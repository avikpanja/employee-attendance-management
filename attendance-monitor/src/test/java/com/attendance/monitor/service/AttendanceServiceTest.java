package com.attendance.monitor.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.attendance.monitor.entity.Attendance;
import com.attendance.monitor.exception.RecordNotFoundException;
import com.attendance.monitor.model.AttendanceData;
import com.attendance.monitor.model.AttendanceState;
import com.attendance.monitor.repo.AttendanceRepository;

@SpringBootTest
class AttendanceServiceTest {

	@MockBean private AttendanceRepository attendanceRepository;
	@Autowired private AttendanceService attendanceService;
	
	private Attendance attendance;
	
	@BeforeEach
	void init() {
		attendance = new Attendance();
		attendance.setEmployeeId(101);
		attendance.setFirstSwipeIn(LocalTime.now().minusHours(8));
		attendance.setLastSwipeOut(LocalTime.now());
		attendance.setRecordDate(LocalDate.now());
		attendance.setState("PRESENT");
		attendance.setTotalHour(8);
	}
	
	@Test
	void testFetchTotalHoursSpentInOneDay() {
		
		when(attendanceRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(attendance));
		
		int totalHourSpent = attendanceService.fetchTotalHoursSpentInOneDay(101, LocalDate.now());
		
		assertEquals(8, totalHourSpent);
	}

	@Test
	void testFetchTotalHoursSpentInOneDay_rcordNotFoundException() {
		
		when(attendanceRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
		
		assertThrows(RecordNotFoundException.class, () -> attendanceService.fetchTotalHoursSpentInOneDay(101, LocalDate.now()));		
	}
}
