package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.StatusHistory;
import com.revature.models.User;




public interface StatusHistoryRepo extends JpaRepository<StatusHistory, Integer> {
	List<StatusHistory> findByUserUserId(int userId);
}
