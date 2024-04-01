package com.avikp.swipe.capture.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avikp.swipe.capture.entity.EmployeeSwipeDetails;
import com.avikp.swipe.capture.entity.SwipeInfo;
import com.avikp.swipe.capture.model.SwipeEvent;
import com.avikp.swipe.capture.model.SwipeEvent.EventType;
import com.avikp.swipe.capture.repo.SwipeInfoRepository;

@Service
public class SwipeService {
	
	private static final Logger logger = LoggerFactory.getLogger(SwipeService.class);
	
	@Autowired
	private SwipeInfoRepository swipeInfoRepo;
	
	public SwipeInfo recordSwipeEvent(SwipeEvent event) {
		
		logger.info("SwipeService::recordSwipeEvent() execution - started ");
		
		Optional<SwipeInfo> swipeInfoOptional = getSwipeDetailsFor(LocalDate.now());
		SwipeInfo info;
		List<EmployeeSwipeDetails> swipeList;
		EmployeeSwipeDetails swipeDetails;
		
		if(swipeInfoOptional.isPresent()) {
			logger.debug("SwipeService::recordSwipeEvent, swipe record present for the day");
			
			info = swipeInfoOptional.get();
			swipeList = info.getEmpSwipeDetailsList();
			OptionalInt index = IntStream.range(0, swipeList.size())
										.filter(
											i -> swipeList.get(i).getEmpId() == event.getEmpId()
										)
										.findFirst();
			if(index.isPresent()) {
				logger.debug("swipe record present for employee - {}",event.getEmpId());
				swipeDetails = swipeList.get(index.getAsInt());
			} else {
				logger.debug("swipe record not present for employee - {}",event.getEmpId());
				swipeDetails = new EmployeeSwipeDetails(event.getEmpId());
				swipeList.add(swipeDetails);
				logger.debug("swipe record inserted for employee - {}",event.getEmpId());
			}
			
		} else {
			swipeList = new ArrayList<>();
			
			swipeDetails = new EmployeeSwipeDetails(event.getEmpId());
			swipeList.add(swipeDetails);
			
			info = new SwipeInfo(
					Integer.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))), 
					swipeList);
		}
		
		if(event.getEventType()==EventType.SWIPE_IN) {
			swipeDetails.getInTimeList().add(LocalTime.now());
		} else {
			swipeDetails.getOutTimeList().add(LocalTime.now());
		}
		logger.info("SwipeService::recordSwipeEvent() execution - completed ");
		return swipeInfoRepo.save(info);
	}
	
//	public List<SwipeInfo> getAllSwipeDetails() {
//		return swipeHistoryRepo.findAll();
//	}
	
	public Optional<SwipeInfo> getSwipeDetailsFor(LocalDate date) {
		Integer id =
				Integer.valueOf(date.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		return swipeInfoRepo.findById(id);
	}
}
