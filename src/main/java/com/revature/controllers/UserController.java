package com.revature.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.CognitoAuth;
import com.revature.dto.EmailList;
import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.services.CohortService;
import com.revature.services.UserService;
import com.revature.utils.CognitoUtil;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CohortService cohortService;

	@Autowired
	private CognitoUtil cUtil;

	Logger log = Logger.getRootLogger();

	@CognitoAuth(role = "user")
	@GetMapping()
	public List<User> findAll() {
		return userService.findAll();
	}

	// need to change this to unique end point
	@GetMapping("id/{id}")
	@CognitoAuth(role = "user")
//	@Logging()
	// Might need to change?
	public User findOneById(@PathVariable int id) {
		return userService.findOneById(id);
	}

	@GetMapping("email/{email}/")
	@CognitoAuth(role = "user")
	public User findOneByEmail(@PathVariable String email) {
		email.toLowerCase();
		return userService.findOneByEmail(email);

	}

	// Need to fix
	@GetMapping("info")
	@CognitoAuth(role = "user")
	public User userInfo() {
		return userService.userInfo();
	}

	@GetMapping("cohorts/{id}")
	@CognitoAuth(role = "user")
//	@Logging()
	public List<User> findAllByCohortId(@PathVariable int id) {
		return userService.findAllByCohortId(id);
	}

	@PostMapping()
	@CognitoAuth(role = "user")
	public User saveUser(@RequestBody User u, HttpServletRequest req) throws IOException, URISyntaxException {
		User checkUser = userService.findOneByEmail(u.getEmail());

		if (checkUser == null) {
			if (cUtil.registerUser(u.getEmail(), req)) {
				return userService.saveUser(u);
			}
		}
		return null;

	}


	@PostMapping("{userid}/cohorts/{cohortid}")
	@CognitoAuth(role = "user")
	public User updateCohort(@PathVariable int userid, @PathVariable int cohortid){
		Cohort cohort = cohortService.findOneByCohortId(cohortid);
		User user = userService.findOneById(userid);
		
		user.getCohorts().add(cohort);
		
		return userService.saveUser(user);
		
	}

	
	
	
	// Need to do something with non created users.
	@PostMapping("emails")
	@CognitoAuth(role = "staging-manager")
	public List<User> saveUsersFromEmails(@RequestBody EmailList emailList, HttpServletRequest req)
			throws IOException, URISyntaxException {
		log.info(emailList);
		List<String> emails = Arrays.asList(emailList.getEmails());
		List<User> createdUsers = new ArrayList<>();
		for (String email : emails) {
			if (userService.findOneByEmail(email) == null) {
				if (cUtil.registerUser(email, req)) {
					User tempUser = userService.saveUser(new User(null, null, email, null));
					if (tempUser != null) {
						createdUsers.add(tempUser);
						continue;
					}
				}
			}
		}
		return createdUsers;

	}
	
	

	@PatchMapping("update/profile")
	@CognitoAuth(role = "user")
	public User updateProfile(@RequestBody User u) {
		User user = userService.updateProfile(u);
		// UserDto or JSON ignore

		return user;
	}
}
