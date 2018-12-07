package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Cohort;
import com.revature.repose.CohortRepo;

@Service
public class CohortServiceSpringDataImpl implements CohortService{

	@Autowired
	CohortRepo cohortRepo;
	
	public String saveCohort(Cohort cohort) {
		return cohortRepo.save(cohort).getCohortToken();
	}

}
