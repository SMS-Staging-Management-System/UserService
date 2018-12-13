package com.revature.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExceptionHandler{

	
	private static final Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,Object>> simpleExceptionHandler() {
		
		log.warn("Bad Handler caught");
		return ResponseEntity.badRequest().body(ResponseMap.getBadResponse());
	}
	
}