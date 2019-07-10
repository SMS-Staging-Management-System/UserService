package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Address;
import com.revature.models.Managers;
import com.revature.repos.AddressRepo;
import com.revature.repos.ManagerRepo;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerRepo managerRepo;
	
	@Autowired
	private AddressRepo addressRepo;
	

	@Override
	public List<Managers> findAll() {
		return managerRepo.findAll();
	}


	@Override
	public List<Managers> findByAddress(String alias) {
		List<Managers> whatever = managerRepo.findByAddress(alias); // turn to the find manager by address 
		return whatever;
	}


	@Override
	public Managers newManager(String email, int address_id) {
		Optional<Address> newAddress = addressRepo.findById(address_id);
		if(newAddress.isPresent()) {
			Managers newManager = new Managers(0, email, newAddress.get());
			managerRepo.save(newManager);
			return newManager;
		} else {
			return null;
		}
	}


	@Override
	public void deleteManager(int id) {
		managerRepo.deleteById(id);
	}


	@Override
	public Managers findById(int id) {
		Optional<Managers> oManage = managerRepo.findById(id);
		if(oManage.isPresent()) {
			return oManage.get();
		} else {
			return null;
		}
	}


	@Override
	public Managers updateManager(Managers manager) {
		managerRepo.save(manager);
		return manager;
	}
	
	
	
}