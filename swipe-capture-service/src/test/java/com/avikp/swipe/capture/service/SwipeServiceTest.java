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
import com.avikp.swipe.capture.model.SwipeEvent;
import com.avikp.swipe.capture.model.SwipeEvent.EventType;
import com.avikp.swipe.capture.repo.SwipeInfoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class SwipeServiceTest {
	
	@MockBean SwipeInfoRepository swipeRepository;
	@Autowired SwipeService swipeService;
	@Autowired ObjectMapper objectMapper;
	
	private SwipeInfo existingSwipeInfo;
	private SwipeInfo expectedSwipeInfo;
	private SwipeEvent swipeEvent;
	
	@BeforeEach
	void init() throws Exception {
		swipeEvent = new SwipeEvent(101, EventType.SWIPE_IN);
		
		List<EmployeeSwipeDetails> employeeWiseSwipeList = new ArrayList<>();
		EmployeeSwipeDetails oneEmpSwipeDetails = new EmployeeSwipeDetails();
		oneEmpSwipeDetails.setEmpId(101);
		oneEmpSwipeDetails.getInTimeList().add(LocalTime.now());
		employeeWiseSwipeList.add(oneEmpSwipeDetails);
		
		existingSwipeInfo = new SwipeInfo(
				Integer.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))), 
				employeeWiseSwipeList);
		
		expectedSwipeInfo = objectMapper
			      .readValue(objectMapper.writeValueAsString(existingSwipeInfo), SwipeInfo.class);
	}

	@Test
	void testRecordSwipeEvent_withNoExistingRecord_dayFirstSwipInEvent() {
		when(swipeRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
		when(swipeRepository.save(ArgumentMatchers.any())).thenReturn(expectedSwipeInfo);
		
		SwipeInfo actualSwipeInfo = swipeService.recordSwipeEvent(swipeEvent);
		assertEquals(expectedSwipeInfo, actualSwipeInfo);
	}
//	
//	@Test
//	void testRecordSwipeEvent_withNoExistingRecord_anySwipOutEvent() {
//		swipeEvent.setEventType(EventType.SWIPE_OUT);
//		EmployeeSwipeDetails employeeSwipeDetails = expectedSwipeInfo.getEmpSwipeDetailsList().get(0);
//		employeeSwipeDetails.getOutTimeList().add(LocalTime.now());
//		
//		when(swipeRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
//		when(swipeRepository.save(ArgumentMatchers.any())).thenReturn(expectedSwipeInfo);
//		
//		SwipeInfo actualSwipeInfo = swipeService.recordSwipeEvent(swipeEvent);
//		assertEquals(expectedSwipeInfo, actualSwipeInfo);
//	}
	
	@Test
	void testRecordSwipeEvent_withExistingRecord_sameDaySwipeOutEvent() {
		// given
		swipeEvent.setEventType(EventType.SWIPE_OUT);
		
		EmployeeSwipeDetails employeeSwipeDetails = expectedSwipeInfo.getEmpSwipeDetailsList().get(0);
		employeeSwipeDetails.getOutTimeList().add(LocalTime.now());

		// when
		when(swipeRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(existingSwipeInfo));
		when(swipeRepository.save(ArgumentMatchers.any())).thenReturn(expectedSwipeInfo);
		
		// then
		SwipeInfo result = swipeService.recordSwipeEvent(swipeEvent);
		assertNotEquals(existingSwipeInfo.getEmpSwipeDetailsList().get(0).getOutTimeList(), 
				result.getEmpSwipeDetailsList().get(0).getOutTimeList().size());
		assertEquals(1, result.getEmpSwipeDetailsList().get(0).getOutTimeList().size());
	}
	
	@Test
	void testRecordSwipeEvent_withExistingRecord_sameDayAnotherEmployeeFirstSwipe() {
		// given
		swipeEvent.setEmpId(102);
		swipeEvent.setEventType(EventType.SWIPE_IN);
		
		EmployeeSwipeDetails empSwipeDetails = new EmployeeSwipeDetails();
		empSwipeDetails.setEmpId(102);
		empSwipeDetails.getInTimeList().add(LocalTime.now());
		expectedSwipeInfo.getEmpSwipeDetailsList().add(empSwipeDetails);

		// when
		when(swipeRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(existingSwipeInfo));
		when(swipeRepository.save(ArgumentMatchers.any())).thenReturn(expectedSwipeInfo);
		
		// then
		SwipeInfo result = swipeService.recordSwipeEvent(swipeEvent);
		assertEquals(2, result.getEmpSwipeDetailsList().size());
	}

}
