package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repos.UserRepo;
import com.revature.utils.IncognitoUtil;

@Service
public class UserServiceSpringDataImpl implements UserService {
	@Autowired
	UserRepo userRepo;
	
	@Override
	public List<User> findAll() {
		return userRepo.findAll();
		
	}

	@Override
	public User findOneById(int id) {
		return userRepo.findOneByUserId(id);
		
	}

	@Override
	public List<User> findAllByCohortId(int id) {
		return userRepo.findAllByCohortsCohortId(id);
//		return null;
	}

	@Override
	public User saveUser(User u) {
		return userRepo.save(u);
	}

	@Override
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
	public User userInfo(HttpServletRequest req) {
//		int id = jwtUtil.extractUserId(req);
//		User u = userRepo.findOneByUserId(id);
		return null;
	}
}
