
package com.revature.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cognito.annotations.CognitoAuth;
import com.revature.cognito.constants.CognitoRoles;
import com.revature.models.User;
import com.revature.models.dto.EmailList;
import com.revature.models.dto.EmailListNoPage;
import com.revature.models.dto.EmailSearch;
import com.revature.services.UserService;

@RestController
@RequestMapping(value = "users")
public class UserController {

	@Autowired
	private UserService userService;

	@CognitoAuth(roles = { "staging-manager" })
	@GetMapping
	String test() {
		return "works";
	}

	@CognitoAuth(roles = { "staging-manager" })
	@GetMapping("allUsers/page/{pageId}")
	public ResponseEntity<Page<User>> findAll(@PathVariable int pageId) {
		Pageable pageable = PageRequest.of(pageId, 7, Sort.by("userId"));
		return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public User findById(@PathVariable int id) {
		return userService.findOneById(id);
	}
	
	@CognitoAuth(roles = { "staging-manager" })
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

		if (resultBody == null) {
			resultStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<User>(resultBody, headers, resultStatus);
	}
	
	@CognitoAuth(roles = { "staging-manager" })
	@GetMapping(path = "paused")
	public List<User> findByStatus() {
		
		return userService.findByStatus();
	}

	@CognitoAuth(roles = { CognitoRoles.STAGING_MANAGER, CognitoRoles.TRAINER })
	@GetMapping("cohorts/{id}")
	public List<User> findAllByCohortId(@PathVariable int id) {
		return userService.findAllByCohortId(id);
	}
	
	
	
	//End point to get all the dropped associate in the last week
		@CognitoAuth(roles = { "staging-manager" })
		@GetMapping("dropped")
		public List<User> findAllDroppedAssociate(){
			return userService.findAllDroppedAssociate();
		}
		
		@GetMapping("dropped/page")
		public Page<User> findAllDroppedAssociatePage(
			@RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
	        @RequestParam(name="pageSize", defaultValue="5") Integer pageSize) {
			// Example url call: ~:8091/reports/InterviewsPerAssociate/page?pageNumber=0&pageSize=3
			// The above url will return the 0th page of size 3.
		    Pageable pageParameters = PageRequest.of(pageNumber, pageSize);
	        return userService.findAllDroppedAssociate(pageParameters);
	    }
	
	
	//the following end point handles search by email request from
	//User Interface by employing findUserByPsrtialEmail() method
	//from UserService interface. It also take cares of pagination (ss)
	//@CognitoAuth(roles = { "staging-manager" })
	@PostMapping(path = "email/partial")
	public ResponseEntity<Page<User>> findUserByEmail(@RequestBody EmailSearch searchParams) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		Pageable pageable = PageRequest.of(searchParams.getPage(), 7, Sort.by("userId"));

		Page<User> resultBody = null;
		HttpStatus resultStatus = HttpStatus.OK;
		try {
			resultBody = userService.findUserByPartialEmail(
					java.net.URLDecoder.decode(searchParams.getEmailFragement().toLowerCase(), "utf-8"), pageable);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		if (resultBody == null) {
			resultStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(resultBody, headers, resultStatus);
	}

	// this endpoint is for receiving a list of user emails(and a page index)
	// and the returning the list of users(paged)
	@CognitoAuth(roles = { "staging-manager" })
	@PostMapping("emails")
	public ResponseEntity<Page<User>> findAllByEmails(@RequestBody EmailList searchParams) {
		Pageable pageable = PageRequest.of(searchParams.getPage(), 7, Sort.by("userId"));
		Page<User> returnResult = userService.findListByEmail(searchParams.getEmailList(), pageable);
		return new ResponseEntity<>(returnResult, HttpStatus.OK);
	}
	
	// endpoint is for receiving a list of user emails and returning the list of users
	@PostMapping("emailnopage")
	public ResponseEntity<List<User>> findAllByEmailNoPage(@RequestBody EmailListNoPage searchParams) {
		List<User> returnResult = userService.findListByEmail(searchParams.getEmailList());
		return new ResponseEntity<>(returnResult, HttpStatus.OK);
	}

	// endpoint is for receiving a list of user emails and returning the list of users
	// the same as emailnopage, needs consolidation
	@CognitoAuth(roles = { "staging-manager" })
	@PostMapping("emailsnotpageable")
	public ResponseEntity<List<User>> findAllByEmailsNotPageable(@RequestBody EmailList searchParams) {
		List<User> returnResult = userService.findListByEmailNotPageable(searchParams.getEmailList());
		return new ResponseEntity<>(returnResult, HttpStatus.OK);
	}

	@PostMapping
	public User save(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@PatchMapping
	public User update(@RequestBody User user) {
		return userService.updateProfile(user);
	}
	//This end point is going to return all sms_users in Staging virtual and not virtual
	@CognitoAuth(roles = { "staging-manager" })
	@GetMapping("invirtual")
	public ResponseEntity<List<User>> findAllInStaging() {
		List<User> returnResult = userService.findAllInStaging();
		return new ResponseEntity<>(returnResult, HttpStatus.OK);
	}
	

}
