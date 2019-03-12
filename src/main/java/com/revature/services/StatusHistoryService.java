package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.StatusHistory;
import com.revature.models.User;


@Service
public interface StatusHistoryService {
	List<StatusHistory> findByUser(int userId);
	
	StatusHistory saveStatusHistory(StatusHistory statusHistory);

}
