package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cognito.annotations.CognitoAuth;
import com.revature.cognito.constants.CognitoRoles;
import com.revature.models.Cohort;
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
	
	@GetMapping
    @CognitoAuth(roles = {CognitoRoles.ADMIN, CognitoRoles.STAGING_MANAGER})
	public List<Cohort> findAll() {
		return cohortService.findAll();
	}
	
	
	@CognitoAuth(roles= {CognitoRoles.STAGING_MANAGER, CognitoRoles.TRAINER})
	@PostMapping
	public Cohort save(@RequestBody Cohort cohort) {
		return cohortService.save(cohort);
	}

}
