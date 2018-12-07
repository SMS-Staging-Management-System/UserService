package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.models.Cohort;
import com.revature.models.User;

@Service
public interface CohortService {
	CohortService currentImplementation = new CohortServiceSpringDataImpl();

	String saveCohort(Cohort cohort);

//	Cohort saveUser(User user, int cohortId);

}
