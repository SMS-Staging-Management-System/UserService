package com.revature.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.CognitoAuth;
import com.revature.models.Cohort;
import com.revature.services.CohortService;
import com.revature.utils.ResponseMap;

@RestController
@RequestMapping("cohorts")
public class CohortController {

	@Autowired
	CohortService cohortService;
	
	@PostMapping()
	@CognitoAuth(role="staging-manager")
	public Cohort saveCohort(@RequestBody Cohort cohort) {
		return cohortService.saveCohort(cohort);
	
	}
	
	
	@GetMapping("users/{id}")
	@CognitoAuth(role="staging-manager")
	public List<Cohort> findAllByTrainerId(@PathVariable int id){
		return cohortService.findAllByTrainerId(id);
	}
	
	@GetMapping()
	@CognitoAuth(role="staging-manager")
	public List<Cohort> findAll(){
		return cohortService.findAll();
	}
	
	@GetMapping("{id}")
	@CognitoAuth(role="staging-manager")
	public Cohort findOneById(@PathVariable int id){
		return cohortService.findOneByCohortId(id);
	}
	
	
	
	
	

}
