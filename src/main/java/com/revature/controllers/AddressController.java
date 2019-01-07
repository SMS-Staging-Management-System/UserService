package com.revature.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Address;

@RestController
@RequestMapping(value = "address")
public class AddressController {

	@GetMapping("is-training-location/{isTrainingLocation}")
	public List<Address> findByIsTrainingLocation(@PathVariable boolean isTrainingLocation) {
		return null;
	}

}
