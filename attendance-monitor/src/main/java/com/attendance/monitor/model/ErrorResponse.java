package com.attendance.monitor.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private Integer status;
	private String error;
	private String errorMessage;
	private List<String> errorMessages;
	private LocalDateTime dateTime;

}
