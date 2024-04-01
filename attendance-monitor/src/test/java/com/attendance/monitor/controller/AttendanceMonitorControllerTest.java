package com.attendance.monitor.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.attendance.monitor.model.RequestData;
import com.attendance.monitor.service.AttendanceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = AttendanceMonitorController.class)
@AutoConfigureMockMvc(addFilters = false)
class AttendanceMonitorControllerTest {

	@MockBean private AttendanceService attendanceService; 
	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	
	@Test
	void testTotalHourSpentInOneDay() throws Exception {
		when(attendanceService.fetchTotalHoursSpentInOneDay(
				ArgumentMatchers.anyInt(), ArgumentMatchers.any())).thenReturn(4);
		
		ResultActions response = mockMvc.perform(
				get("/attendance/total-hour")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new RequestData(101, "2024-03-30"))));
		
		response.andExpect(MockMvcResultMatchers.jsonPath("$.data.totalHour").value(4));
	}

}
