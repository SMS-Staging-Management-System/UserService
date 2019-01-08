package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {
	List<Address> findByIsTrainingLocation(boolean isTrainingLocation);

}
