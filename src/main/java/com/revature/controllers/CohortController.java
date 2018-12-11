package com.revature.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.services.CohortService;

@RestController
@RequestMapping("cohort")
public class CohortController {

	@Autowired
	CohortService cohortService;
	
	
	@PostMapping()
	public String saveCohort(@RequestBody Cohort cohort) {
		return cohortService.saveCohort(cohort);

	}
	
//	@GetMapping("id/{id}")
//	public Cohort findOneById(@PathVariable int id){
//		return cohortService.findOneByCohortId(id);
//	}
	
//	@PostMapping("id/{cohortId}")
//	public Cohort saveUser(@RequestBody User user, @PathVariable int cohortId) {
//		return cohortService.saveUser(user, cohortId);
//	}
}
