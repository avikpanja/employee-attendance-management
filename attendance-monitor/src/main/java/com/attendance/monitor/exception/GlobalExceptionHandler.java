package com.attendance.monitor.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.attendance.monitor.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex) {
		ErrorResponse er = new ErrorResponse();
		er.setDateTime(LocalDateTime.now());
		er.setError(HttpStatus.NOT_FOUND.name());
		er.setStatus(HttpStatus.NOT_FOUND.value());		
	    er.setErrorMessage(ex.getMessage());
	    
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
	}
}
