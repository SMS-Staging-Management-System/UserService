package com.revature.services;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.revature.models.User;
import com.revature.utils.JwtUtil;

public class UserServiceSpringDataImpl {

	@Autowired
	private JwtUtil jwtUtil;
	
	public Map<String,Object> login(User user) {
		User tUser = userRepo.findByUsername(user.getUsername());
		if (tUser != null) {
			if(BCrypt.checkpw(user.getPassword(), tUser.getPassword())){
				try {
					 Map<String, Object> tMap = new HashMap<>();
					 tMap.put("User", user);
					 tMap.put("Jwt",jwtUtil.createJwt(user));
					 return tMap;
				} catch (IOException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
	
	
	public User userInfo(HttpServletRequest req) {
		int id = jwtUtil.extractUserId(req);
		User u = userRepo.findOne(id);
		return u;
	}
}
