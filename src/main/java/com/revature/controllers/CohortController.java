package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.CognitoAuth;
import com.revature.dto.CohortUserListInputDto;
import com.revature.dto.CohortUserListOutputDto;
import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.services.CohortService;
import com.revature.services.UserService;
import com.revature.utils.CognitoUtil;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("cohorts")
public class CohortController {

	@Autowired
	CohortService cohortService;

	@Autowired
	UserService userService;

	@Autowired
	CognitoUtil cognitoUtil;


//Allow cohort with no user list

	@PostMapping()
	@CognitoAuth(role = "staging-manager")
	public ResponseEntity<CohortUserListOutputDto> saveCohortWithUserList(@RequestBody CohortUserListInputDto cuList,
			HttpServletRequest req) throws IOException {
		
		CohortUserListOutputDto cuListOutput = cohortService.saveCohortWithUserList(cuList,req);
		if (cuListOutput.getCohort() == null) {
			cuListOutput.setMessages("Cohort could not be created or already exists, all users rejected");
			return ResponseEntity.status(400).body(cuListOutput);
		}

		if (cuList.getUserList() == null) {
			cuListOutput.setMessages("Created cohort with no users");
			return ResponseEntity.status(200).body(cuListOutput);
		}
		
		return ResponseEntity.status(200).body(cuListOutput);

	}

	@GetMapping("users/{id}")
	@CognitoAuth(role = "staging-manager")
	public ResponseEntity<List<Cohort>>findAllByTrainerId(@PathVariable int id) {
		return ResponseEntity.status(200).body(cohortService.findAllByTrainerId(id));
	}

	@GetMapping()
	@CognitoAuth(role = "staging-manager")
	public ResponseEntity<List<Cohort>> findAll() {
		return ResponseEntity.status(200).body(cohortService.findAll());
	}

	@GetMapping("{id}")
	@CognitoAuth(role = "staging-manager")
	public ResponseEntity<Cohort> findOneById(@PathVariable int id) {
		return ResponseEntity.status(200).body(cohortService.findOneByCohortId(id));
	}

}
