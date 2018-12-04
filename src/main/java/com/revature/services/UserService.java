package com.revature.services;

import java.util.Map;

import com.revature.models.User;

public interface UserService {

	
	Map<String,Object> login(User u);
	
}
