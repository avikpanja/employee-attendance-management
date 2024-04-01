package com.avikp.swipe.capture.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.avikp.swipe.capture.entity.EmployeeSwipeDetails;
import com.avikp.swipe.capture.entity.SwipeInfo;
import com.avikp.swipe.capture.model.SwipeEvent;
import com.avikp.swipe.capture.model.SwipeEvent.EventType;
import com.avikp.swipe.capture.service.SwipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = SwipeController.class)
@AutoConfigureMockMvc(addFilters = false)
class SwipeControllerTest {

	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;
	@MockBean SwipeService swipeService;
	
	private SwipeInfo swipeInfo;
	private SwipeEvent swipeEvent;
	
	@BeforeEach
	void init() {
		swipeEvent = new SwipeEvent(101, EventType.SWIPE_IN);
		
		List<EmployeeSwipeDetails> swipeList = new ArrayList<>();
		EmployeeSwipeDetails swipeDetails = new EmployeeSwipeDetails(101);
		swipeList.add(swipeDetails);
		
		swipeInfo = new SwipeInfo(
				Integer.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))), 
				swipeList);
	}
	
	@Test
	void testRecordSwipeEvent() throws Exception {
		when(swipeService.recordSwipeEvent(ArgumentMatchers.any())).thenReturn(swipeInfo);
		
		ResultActions response = mockMvc.perform(
				post("/swipe/record")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(swipeEvent)));
		
		response.andExpect(MockMvcResultMatchers.status().isAccepted());
		
	}
	
	@Test
	void testGetAllDeatils() throws Exception {
		when(swipeService.recordSwipeEvent(ArgumentMatchers.any())).thenReturn(swipeInfo);
		
		ResultActions response = mockMvc.perform(
				post("/swipe/record")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(swipeEvent)));
		
		response.andExpect(MockMvcResultMatchers.status().isAccepted());
		
	}

}
