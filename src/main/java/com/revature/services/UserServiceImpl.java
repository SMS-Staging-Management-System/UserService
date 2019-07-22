package com.revature.services;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revature.cognito.dtos.CognitoRegisterBody;
import com.revature.cognito.intercomm.CognitoClient;
import com.revature.feign.FeignException;
import com.revature.models.StatusHistory;
import com.revature.models.User;
import com.revature.repos.AddressRepo;
import com.revature.repos.StatusHistoryRepo;
import com.revature.repos.UserRepo;
import com.revature.util.ListToPage;

@Service
public class UserServiceImpl implements UserService {

//	List<String> roles = cognitoUtil.getRequesterRoles();
  
	@Value("${cognito.key}")
	private String cognitoKey;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private StatusHistoryRepo statusHistoryRepo;

	@Autowired
	private CognitoClient cognitoClient;
	
	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepo.findAll(pageable);
	}

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
		} catch (FeignException e) {
			// can occur if the user is already in cognito
		}
		if (userRepo.findByEmailIgnoreCase(u.getEmail()) != null) {
			return null;
		} else {

			addressRepo.save(u.getPersonalAddress());
			User savedUser = userRepo.save(u);

			StatusHistory statusHistory = new StatusHistory();
			statusHistory.setAddress(savedUser.getTrainingAddress());
			statusHistory.setUser(savedUser);
			statusHistory.setStatus(savedUser.getUserStatus());
			statusHistoryRepo.save(statusHistory);

			return savedUser;
		}
	}

	// Can only change number, first and last name at the moment
	// TODO need to be able to update personal address
	@Override
	@Transactional
	public User updateProfile(User u) {
		Optional<User> oldUser = userRepo.findById(u.getUserId());

		if (oldUser.isPresent()) {
			if (u.getTrainingAddress() != null && u.getFirstName() != null && u.getLastName() != null) {
				if (u.getPersonalAddress() != null) {
					u.setPersonalAddress(addressRepo.save(u.getPersonalAddress()));
				}
				if (!oldUser.get().getUserStatus().equals(u.getUserStatus())) {
					StatusHistory statusHistory = new StatusHistory();
					statusHistory.setAddress(u.getTrainingAddress());
					statusHistory.setUser(u);
					statusHistory.setStatus(u.getUserStatus());
					statusHistoryRepo.save(statusHistory);
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
	
	//The following implements findUserByPartialEmail() method
	//in UserService interface by using the findUserByEmailIgnoreCase()
	//Query method from User repository (ss)
	@Override 
	public Page<User> findUserByPartialEmail(String email, Pageable pageable) {
		return userRepo.findUsersByEmailIgnoreCase(email, pageable);
	}
  
	@Override
	public Page<User> findListByEmail(List<String> emailList, Pageable pageable) {
		System.out.println(emailList);
		List<String> lowerCaseEmailList = emailList.stream().map(email -> email.toLowerCase(Locale.ENGLISH))
				.collect(Collectors.toList());
		System.out.println(lowerCaseEmailList);

		return userRepo.findAllUserByEmailIgnoreCase(lowerCaseEmailList, pageable);
	}

	@Override
	public List<User> findListByEmail(List<String> emailList) {
		List<String> lowerCaseEmailList = emailList.stream().map(email -> email.toLowerCase(Locale.ENGLISH))
				.collect(Collectors.toList());
		return userRepo.findAllUserByEmailIgnoreCase(lowerCaseEmailList);
	}
  
	@Override
	public List<User> findListByEmailNotPageable(List<String> emailList) {
		System.out.println(emailList);
		List<String> lowerCaseEmailList = emailList.stream().map(email -> email.toLowerCase(Locale.ENGLISH))
				.collect(Collectors.toList());
		System.out.println(lowerCaseEmailList);

		return userRepo.findAllUserByEmailIgnoreCaseNotPageable(lowerCaseEmailList);
	}
	
	@Override
	public List<User> findByStatus() {
		return userRepo.findByStatus();
	}	

	@Override
	public List<User> findAllInStaging() {
		
		return userRepo.findAllInStaging();

	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmailIgnoreCase(email);
	}
	
	@Override
	public List<User> findAllDroppedAssociate() {
		return userRepo.findAllDroppedAssociate().stream().distinct().collect(Collectors.toList());	
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public Page<User> findAllDroppedAssociate(Pageable page) {
		PageImpl PI = ListToPage.getPage(findAllDroppedAssociate(), page);
		return PI;
	}

}

