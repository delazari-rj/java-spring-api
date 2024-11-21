package com.delazari.java_spring_api.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Unknown error.", e.getMessage(), request.getRequestURI());
		
		if(e.getMessage().equalsIgnoreCase("There is a card with the same name already registered.")) {
			status = HttpStatus.CONFLICT;
			err = new StandardError(System.currentTimeMillis(), status.value(), "Card name conflict.", e.getMessage(), request.getRequestURI());
		}
		
		if(e.getMessage().equalsIgnoreCase("The value of the Name field is invalid.")) {
			status = HttpStatus.UNPROCESSABLE_ENTITY;
			err = new StandardError(System.currentTimeMillis(), status.value(), "The value is invalid.", e.getMessage(), request.getRequestURI());
		}
		
		return ResponseEntity.status(status).body(err);
	}
}
