package com.revature.controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cognito.annotations.CognitoAuth;
import com.revature.cognito.constants.CognitoRoles;
import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.services.CohortService;

@RestController
@RequestMapping("cohorts")
public class CohortController {

	@Autowired
	private CohortService cohortService;

	@GetMapping("trainer/{trainerId}")
	public List<Cohort> findByTrainer(@PathVariable int trainerId) {
		return cohortService.findByTrainer(trainerId);
	}
	
	@GetMapping("/address/id/{addressId}")
	@CognitoAuth(roles = {CognitoRoles.ADMIN, CognitoRoles.STAGING_MANAGER})
	public Page<Cohort> findAllByAddressAddressId(@PathVariable int addressId, @RequestParam int page) {
		return cohortService.findAllByAddressAddressId(addressId, page);
	}

	@GetMapping("/trainer/id/{userId}")
	@CognitoAuth(roles = {CognitoRoles.ADMIN, CognitoRoles.STAGING_MANAGER})
	public Page<Cohort> findAllByTrainerUserId(@PathVariable int userId, @RequestParam int page) {
		return cohortService.findAllByTrainerUserId(userId, page);
	}

	@GetMapping("/trainer/{email:.+}/email")
	@CognitoAuth(roles = {CognitoRoles.ADMIN, CognitoRoles.STAGING_MANAGER})
	public Page<Cohort> findAllByTrainerEmail(@PathVariable String email, @RequestParam int page) {
		return cohortService.findAllByTrainerEmail(email, page);
	}

	@GetMapping
	@CognitoAuth(roles = {CognitoRoles.ADMIN, CognitoRoles.STAGING_MANAGER})
	public Page<Cohort> findAll(@RequestParam int page) {
		System.out.println(page);
		System.out.println("findall");
		return cohortService.findAll(page);
	}

	@GetMapping("users/id/{id}")
    @CognitoAuth(roles = {CognitoRoles.ADMIN, CognitoRoles.STAGING_MANAGER})
	public Set<User> findCohortUsers(@PathVariable int id) {
		return cohortService.findCohortUsers(id);
	}
	
	@CognitoAuth(roles= {CognitoRoles.STAGING_MANAGER, CognitoRoles.TRAINER})
	@PostMapping
	public Cohort save(@RequestBody Cohort cohort) {
		return cohortService.save(cohort);
	}
	
	@GetMapping("prestaging/{epochDate}")
	public List<Cohort> findPreStagingCohorts(@PathVariable long epochDate) {
		
		// Epoch dates are easier to pass, but...
		// convert the epoch date milliseconds to a LocalDate using
		// the system's current time zone as the default time zone
		// and using the Instant class to do the conversion,
		// because this is being stored in the model as a LocalDate
		// object instead of a Date object
		LocalDate date = Instant.ofEpochMilli(epochDate).atZone(ZoneId.systemDefault()).toLocalDate();
		return cohortService.findEndingCohorts(date);
	}
	
//	Get cohort by token
	@GetMapping("token/{cohortToken}")
	public Cohort findCohortByToken(@PathVariable String cohortToken) {
		Cohort foundCohort = cohortService.findCohortByToken(cohortToken);
		return foundCohort;
	}
	
	@PostMapping("token/{cohortToken}")
	public ResponseEntity<String> joinCohort(@RequestBody User user, @PathVariable String cohortToken) {
			String status = cohortService.joinCohort(user, cohortToken);
			switch (status) {//This is not enough responses, will fix in post
			case "Not Found":
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			case "Bad Request":
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			case "OK":
				return new ResponseEntity<String>(HttpStatus.OK);

			default:
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<String> handleError() {
		return new ResponseEntity<String>("Database Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}


