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
	public ResponseEntity<Map<String,Object>> saveCohort(@RequestBody Cohort cohort) {
		Cohort sCohort =  cohortService.saveCohort(cohort);
		if (sCohort == null) {
			return  ResponseEntity.badRequest().body(ResponseMap.getBadResponse("Cohort not saved."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(sCohort,"Saved Cohort"));
	
	}
	
	
	@GetMapping("users/{id}")
	@CognitoAuth(role="staging-manager")
	public ResponseEntity<Map<String,Object>> findAllByUserId(@PathVariable int id){
		List<Cohort> sCohort =  cohortService.findAllByTrainerId(id);
		if (sCohort == null) {
			return  ResponseEntity.status(203).body(ResponseMap.getBadResponse("Cohorts Not found."));
		}
		return  ResponseEntity.ok().body(ResponseMap.getGoodResponse(sCohort,"Here's all your cohorts."));
	}
}
