package com.avikp.swipe.capture.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
	
	private Integer status;
	private String error;
	private String errorMessage;
	private List<String> errorMessages;
	private LocalDateTime dateTime;
	
}
