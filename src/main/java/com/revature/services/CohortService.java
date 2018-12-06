package com.revature.services;

import org.springframework.stereotype.Service;

import com.revature.models.Cohort;

@Service
public interface CohortService {
	CohortService currentImplementation = new CohortServiceSpringDataImpl();

	String saveCohort(Cohort cohort);

	
}
