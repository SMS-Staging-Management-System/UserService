package com.revature.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler{

	
	private static final Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> badRequest() {
		
		log.warn("Bad Request user");
		return new ResponseEntity<String>("Unhandled Request",HttpStatus.BAD_REQUEST);
	}
	
}