package com.avikp.swipe.capture.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.avikp.swipe.capture.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ErrorResponse er = new ErrorResponse();
		er.setDateTime(LocalDateTime.now());
		er.setError(HttpStatus.BAD_REQUEST.toString());
		er.setStatus(HttpStatus.BAD_REQUEST.value());
		
		List<String> errors = new ArrayList<>();
	    for (FieldError fieldError : e.getFieldErrors()) {
	        String fieldName = fieldError.getField();
	        String errorMessage = fieldError.getDefaultMessage();
	        errors.add(String.format("%s: %s", fieldName, errorMessage));
	    }
	    er.setErrorMessages(errors);
		return ResponseEntity.badRequest().body(er);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex) {
		
		logger.debug(ex.getMessage(), ex.getCause());
		
		ErrorResponse er = new ErrorResponse();
		er.setDateTime(LocalDateTime.now());
		er.setError(HttpStatus.NOT_FOUND.name());
		er.setStatus(HttpStatus.NOT_FOUND.value());		
	    er.setErrorMessage(ex.getMessage());
	    
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
	}
}
	