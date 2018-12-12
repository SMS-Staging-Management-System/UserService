package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Cohort;

@Service
public interface CohortService {
	CohortService currentImplementation = new CohortServiceSpringDataImpl();

	Cohort saveCohort(Cohort cohort);

	List<Cohort> findAllByUserId(int id);

	
}
