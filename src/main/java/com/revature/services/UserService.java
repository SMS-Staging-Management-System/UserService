package com.revature.services;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.revature.models.User;

@Service 
public interface UserService {

	UserService currentImplementation = new UserServiceImpl();
	
	public List<User> findAll();
	public User findOneById(int id);
	public List<User> findAllByCohortId(int id);
	public User saveUser(User u);
	public User updateProfile(User u);
	public User userInfo();
	public User findOneByUsername(String username);
	public User findOneByEmail(String email);
}
