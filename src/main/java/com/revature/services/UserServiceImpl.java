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
import com.revature.repos.AddressRepo;
import com.revature.repos.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${cognito.key}")
	private String cognitoKey;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AddressRepo addressRepo;

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

	// Can only change number, first and last name at the moment
	// TODO need to be able to update personal address
	@Override
	@Transactional
	public User updateProfile(User u) {
		if (userRepo.findById(u.getUserId()) != null)
		{
			if (u.getTrainingAddress() != null && u.getFirstName() != null &&
				u.getLastName() != null) {
				if (u.getPersonalAddress() != null) {
					u.setPersonalAddress(addressRepo.save(u.getPersonalAddress()));
				}
					return userRepo.save(u);
			}
		}
		
		return null;
	}

	@Override
	public User findOneByEmail(String email) {
		return userRepo.findByEmailIgnoreCase(email);
	}
	

}
