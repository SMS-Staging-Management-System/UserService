package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Managers;
import com.revature.services.ManagerService;

@RestController
@RequestMapping(value = "managers")
public class ManagersController {
	
	@Autowired
	private ManagerService managersService;
	
	@GetMapping("{id}")
	public Managers getManagersById(@PathVariable int id) {
		return managersService.findById(id);
	}
	
	@GetMapping("")
	public List<Managers> getAllManagers(){
		return managersService.findAll();
	}
	
	@GetMapping("address/{alias}")
	public List<Managers> getManagersByAddress(@PathVariable String alias){
		return managersService.findByAddress(alias);
	}
	
	@PostMapping("new")
	public void saveNewManager(@RequestBody String email, int address_id) {
		managersService.newManager(email, address_id);
	}
	
	@PostMapping("update")
	public void updateManager(@RequestBody Managers manager) {
		managersService.updateManager(manager);
	}
	
	@DeleteMapping("")
	public void deleteManager(int id) {
		managersService.deleteManager(id);
	}
}