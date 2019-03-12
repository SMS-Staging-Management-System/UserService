package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.StatusHistory;
import com.revature.models.User;
import com.revature.repos.StatusHistoryRepo;


@Service
public class StatusHistoryServiceImpl implements StatusHistoryService{

	@Autowired
	private StatusHistoryRepo statusHistoryRepo;
	
	
	@Override
	public List<StatusHistory> findByUser(int userId) {
		// TODO Auto-generated method stub
		return statusHistoryRepo.findByUserUserId(userId);
	}

	@Override
	public StatusHistory saveStatusHistory(StatusHistory statusHistory) {
		// TODO Auto-generated method stub
		return statusHistoryRepo.save(statusHistory);
	}

}
