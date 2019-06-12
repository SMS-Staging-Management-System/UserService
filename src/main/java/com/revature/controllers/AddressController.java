package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Address;
import com.revature.services.AddressService;

@RestController
@RequestMapping(value = "addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping("is-training-location/{isTrainingLocation}")
	public List<Address> findByIsTrainingLocation(@PathVariable boolean isTrainingLocation) {
		return addressService.findByIsTrainingLocation(isTrainingLocation);
	}

}
