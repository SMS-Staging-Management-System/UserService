package com.revature.services;

import java.util.List;

import com.revature.models.Address;

public interface AddressService {
	List<Address> findByIsTrainingLocation(boolean isTrainingLocation);
}
