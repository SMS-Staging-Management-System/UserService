package com.revature.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User not found")  // 404
public class UserExceptionHandler extends RuntimeException {

	/**
	 * 
	 */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
	private static final long serialVersionUID = 1L;

	public UserExceptionHandler(int id) {
		
	}
	
}