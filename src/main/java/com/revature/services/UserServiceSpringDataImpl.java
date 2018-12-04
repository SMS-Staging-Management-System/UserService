package com.revature.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repos.UserRepo;

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
//		userRepo.findAllByCohort(id);
		return null;
	}

	@Override
	public User saveUser(User u, int cohortToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
