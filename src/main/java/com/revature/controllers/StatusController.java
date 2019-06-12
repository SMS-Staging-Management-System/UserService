package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Status;
import com.revature.services.StatusService;

@RestController
@RequestMapping(value = "status")
public class StatusController {

	@Autowired
	private StatusService statusService;

	
	@GetMapping
	List<Status> findAll() {
		return statusService.findAll();
	}
	
	
}
