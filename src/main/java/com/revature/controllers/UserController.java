package com.revature.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.CognitoAuth;
import com.revature.dto.CohortUserListOutputDto;
import com.revature.dto.UserListInputDto;
import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.services.CohortService;
import com.revature.services.UserService;
import com.revature.utils.CognitoUtil;
import com.revature.utils.ResponseEntityUtil;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CohortService cohortService;

	@Autowired
	private CognitoUtil cognitoUtil;
	
	@Autowired
	private ResponseEntityUtil responseEntity;

	Logger log = Logger.getRootLogger();

	@CognitoAuth(role = "user")
	@GetMapping()
	public ResponseEntity<List<User>> findAll() {
		
		return responseEntity.getResponseEntityUserList(userService.findAll());
	}

	// need to change this to unique end point
	@GetMapping("id/{id}")
	@CognitoAuth(role = "user")
//	@Logging()
	// Might need to change?
	public ResponseEntity<User> findOneById(@PathVariable int id) {

		return responseEntity.getResponseEntity(userService.findOneById(id));
	}

	@GetMapping("email/{email}/")
	@CognitoAuth(role = "user")
	public ResponseEntity<User> findOneByEmail(@PathVariable String email) {		
		return responseEntity.getResponseEntity(userService.findOneByEmail(email.toLowerCase()));
	}

	// Need to fix
	@GetMapping("info")
	@CognitoAuth(role = "user")
	public ResponseEntity<User> userInfo() {
		
		return responseEntity.getResponseEntity(userService.userInfo());
	}

	@GetMapping("cohorts/{id}")
	@CognitoAuth(role = "user")
//	@Logging()
	public ResponseEntity<List<User>> findAllByCohortId(@PathVariable int id) {
		return  ResponseEntity.status(200).body(userService.findAllByCohortId(id));
	}

	@PostMapping()
	@CognitoAuth(role = "user")
	public ResponseEntity<User> saveUser(@RequestBody User u, HttpServletRequest req) throws IOException, URISyntaxException {

		return  responseEntity.getResponseEntitySaveUser(cognitoUtil.registerUser(u, req));

	}

	@PatchMapping("{userid}/cohorts/{cohortid}")
	@CognitoAuth(role = "user")
	public ResponseEntity<User> updateCohort(@PathVariable int userid, @PathVariable int cohortid) {
		Cohort cohort = cohortService.findOneByCohortId(cohortid);
		User user = userService.findOneById(userid);
		user.getCohorts().add(cohort);

		return responseEntity.getResponseEntity(userService.saveUser(user));
	}

	// Need to do something with non created users.
	@PostMapping("cohorts/{id}")
	@CognitoAuth(role = "staging-manager")
	public ResponseEntity<CohortUserListOutputDto> saveUsers(@RequestBody UserListInputDto userList, @PathVariable int id,
			HttpServletRequest req) throws IOException, URISyntaxException {
		
		CohortUserListOutputDto cuListOutput = userService.saveUsers(userList,id,req);
		
	
		return ResponseEntity.status(200).body(cuListOutput);

	}

	@PatchMapping()
	@CognitoAuth(role = "user")
	public ResponseEntity<User> updateProfile(@RequestBody User u) {
		return  responseEntity.getResponseEntity(userService.updateProfile(u));
	}

}