package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.cognito.dtos.CognitoRegisterBody;
import com.revature.cognito.intercomm.CognitoClient;
import com.revature.cognito.utils.CognitoUtil;
import com.revature.models.User;
import com.revature.repos.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired 
	private CognitoClient cognitoClient;
	
	@Autowired
	private CognitoUtil cognitoUtil;

	@Override
	public User findOneById(int id) {
		return userRepo.getOne(id);
	}

	@Override
	public List<User> findAllByCohortId(int id) {
		return userRepo.findAllByCohortsCohortId(id);
	}

	@Override
	public User saveUser(User u) {
		User savedUser = userRepo.saveAndFlush(u);
		// make a call to register the new user with cognito
		cognitoClient.registerUser(cognitoUtil.getCurrentUserToken(), new CognitoRegisterBody(u.getEmail()));
		return savedUser;
	}

	@Override
	public User updateProfile(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOneByEmail(String email) {
		return userRepo.findByEmailIgnoreCase(email);
	}

}
