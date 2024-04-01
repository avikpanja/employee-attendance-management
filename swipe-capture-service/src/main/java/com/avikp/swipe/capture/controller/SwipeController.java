package com.avikp.swipe.capture.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avikp.swipe.capture.model.SuccessResponse;
import com.avikp.swipe.capture.model.SwipeEvent;
import com.avikp.swipe.capture.service.SwipeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("swipe/")
public class SwipeController {
	private static final Logger logger = LoggerFactory.getLogger(SwipeController.class);
	
	@Autowired SwipeService swipeService;
	
	@PostMapping("record")
	public ResponseEntity<SuccessResponse> recordSwipeEvent(@Valid @RequestBody SwipeEvent event) {
		swipeService.recordSwipeEvent(event);
		
		logger.info("SwipeController::recordSwipeEvent() execution - completed");
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new SuccessResponse(HttpStatus.ACCEPTED.value(), "Event recorded", LocalDateTime.now()));
	}

//	@GetMapping("records")
//	public ResponseEntity<List<SwipeInfo>> getAllDeatils() {
//		return ResponseEntity.ok(swipeService.getAllSwipeDetails());
//	}

}
