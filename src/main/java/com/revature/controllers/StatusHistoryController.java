package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.StatusHistory;

import com.revature.services.StatusHistoryService;


@RestController
@RequestMapping(value = "statushistory")
public class StatusHistoryController {
	
	@Autowired
	private StatusHistoryService statusHistoryService;
	
	
	@GetMapping("user/{userid}")
	public List<StatusHistory> findByUser(@PathVariable int userid) {
		return statusHistoryService.findByUser(userid);
	}
	
	@PostMapping
	public StatusHistory saveStatusHistory(@RequestBody StatusHistory statusHistory) {
		return statusHistoryService.saveStatusHistory(statusHistory);
	}
	
//	TODO: create a check for the role of the user here so that users cannot change the data of other users in Postman for example
	

}
