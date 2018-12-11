package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.CognitoAuth;
import com.revature.models.Cohort;
import com.revature.services.CohortService;

@RestController
@RequestMapping("cohort")
public class CohortController {

	@Autowired
	CohortService cohortService;
	
	@PostMapping()
	@CognitoAuth(highestRole="user")
	public String saveCohort(@RequestBody Cohort cohort) {
		return cohortService.saveCohort(cohort);

	}
}
