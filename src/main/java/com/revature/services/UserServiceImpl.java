package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.revature.cognito.dtos.CognitoRegisterBody;
import com.revature.cognito.intercomm.CognitoClient;
import com.revature.cognito.utils.CognitoUtil;
import com.revature.feign.FeignException;
import com.revature.models.User;
import com.revature.repos.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${cognito.key}")
	private String cognitoKey;
	
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
	@Transactional
	public User saveUser(User u) {
		// make a call to register the new user with cognito
		try {
			cognitoClient.registerUser(cognitoKey, new CognitoRegisterBody(u.getEmail()));
		} catch(FeignException e) {
			// can occur if the user is already in cognito
		}
		if (userRepo.findByEmailIgnoreCase(u.getEmail()) != null) {
			return null;
		} else {
			User savedUser = userRepo.saveAndFlush(u);
			return savedUser;
		}
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
