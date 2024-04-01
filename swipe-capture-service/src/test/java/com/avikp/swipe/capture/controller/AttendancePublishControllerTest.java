package com.avikp.swipe.capture.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.avikp.swipe.capture.model.AttendanceData;
import com.avikp.swipe.capture.service.AttendanceCalculateService;
import com.avikp.swipe.capture.service.KafkaPublisher;

@WebMvcTest(controllers = AttendancePublishController.class)
@AutoConfigureMockMvc(addFilters = false)
class AttendancePublishControllerTest {

	@Autowired private MockMvc mockMvc;
	@MockBean private AttendanceCalculateService acService;
	@MockBean private KafkaPublisher kafkaPublisher;
	
	private List<AttendanceData> attendanceList;
	
	@BeforeEach
	void init() {
		attendanceList = List.of(
				AttendanceData.newObject(
						LocalDate.now(), LocalTime.now().minusHours(8), 
						LocalTime.now(), 101)
				);
	}
	
	@Test
	void testCalculateAndPublishAttendance() throws Exception {
		when(acService.calculateEmployeeWiseAttendanceForToday()).thenReturn(attendanceList);
		doNothing().when(kafkaPublisher).publishAttendanceData(ArgumentMatchers.any());
		
		ResultActions response = mockMvc.perform(
				get("/publish/attendance"));
		
		response.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
