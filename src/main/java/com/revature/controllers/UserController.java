package com.revature.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cognito.annotations.CognitoAuth;
import com.revature.cognito.constants.CognitoRoles;
import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping(value = "users")
public class UserController {

	@Autowired
	private UserService userService;

	@CognitoAuth(roles = { "staging-manager" })
	@GetMapping
	String findAll() {
		return "works";
	}

	@GetMapping("{id}")
	public User findById(@PathVariable int id) {
		return userService.findOneById(id);
	}

	@GetMapping(path = "email/{email:.+}")
	public ResponseEntity<User> findByEmail(@PathVariable String email) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		
		User resultBody = null;
		HttpStatus resultStatus = HttpStatus.OK;
		try {
			resultBody = userService.findOneByEmail(java.net.URLDecoder.decode(email.toLowerCase(), "utf-8"));
			} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} 
		
		if(resultBody == null) {
			resultStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<User>(resultBody, headers, resultStatus);
	}

	@CognitoAuth(roles = { CognitoRoles.STAGING_MANAGER, CognitoRoles.TRAINER })
	@GetMapping("cohorts/{id}")
	public List<User> findAllByCohortId(@PathVariable int id) {
		return userService.findAllByCohortId(id);
	}

	//@CognitoAuth(roles = { "staging-manager" })
	@PostMapping
	public User save(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PatchMapping
	public User update(@RequestBody User user) {
		return userService.updateProfile(user);
	}

}
