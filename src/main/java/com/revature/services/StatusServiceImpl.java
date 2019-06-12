package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Status;
import com.revature.repos.StatusRepo;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepo statusRepo;
	
	@Override
	public List<Status> findAll() {
		return statusRepo.findAll();
	}

}
