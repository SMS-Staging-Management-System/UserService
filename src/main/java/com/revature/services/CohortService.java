package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Cohort;

@Service
public interface CohortService {
	CohortService currentImplementation = new CohortServiceImpl();

	Cohort saveCohort(Cohort cohort);

	List<Cohort> findAllByTrainerId(int id);


	Cohort findOneByCohortId(int id);

	List<Cohort> findAll();

	
}
