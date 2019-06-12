package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Address;
import com.revature.repos.AddressRepo;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepo addressRepo;

	@Override
	public List<Address> findByIsTrainingLocation(boolean isTrainingLocation) {
		return addressRepo.findByIsTrainingLocation(isTrainingLocation);
	}

}
