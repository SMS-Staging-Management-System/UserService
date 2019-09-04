package com.revature.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revature.models.Cohort;
import com.revature.models.User;

@Service
public interface CohortService {

	Set<User> findCohortUsers(int id);
	
	List<Cohort> findByTrainer(int trainerId);

	Cohort save(Cohort cohort);
	
	List<Cohort> findAll();

	Page<Cohort> findAllByPage(Pageable pageable);
	
	String joinCohort(User user, String cohortToken) ;
  
	List<Cohort> findEndingCohorts(LocalDate date);

	Cohort findCohortByToken(String token);
}
