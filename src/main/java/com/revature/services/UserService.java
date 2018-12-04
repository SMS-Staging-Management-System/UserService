package com.revature.services;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.revature.models.User;

@Service 
public interface UserService {

	UserService currentImplementation = new UserServiceSpringDataImpl();
	
	public List<User> findAll();
	public User findOneById(int id);
	public List<User> findAllByCohortId(int id);
	public User saveUser(User u,int cohortToken);
	public Map<String,Object>  login(User u);
	public User updateUser(User u);
	public User userInfo(HttpServletRequest req);
}
