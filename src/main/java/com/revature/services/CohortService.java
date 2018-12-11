package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.models.Cohort;
import com.revature.models.User;

@Service
public interface CohortService {
	CohortService currentImplementation = new CohortServiceSpringDataImpl();

	String saveCohort(Cohort cohort);

	User verifyToken(String token);

//	Cohort saveUser(User user, int cohortId);

}
