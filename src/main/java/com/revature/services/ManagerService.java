package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Address;
import com.revature.models.Managers;

@Service
public interface ManagerService {
	public List<Managers> findAll();
	
	public Managers findById(int id);
	
	public List<Managers> findByAddress(String alias);
	
	public Managers newManager(String email, int address_id);
	
	public Managers updateManager(Managers woop);
	
	public void deleteManager(int id);
}