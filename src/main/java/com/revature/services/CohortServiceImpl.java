package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Cohort;
import com.revature.repos.CohortRepo;

@Service
public class CohortServiceImpl implements CohortService{

	@Autowired
	CohortRepo cohortRepo;
	
	public Cohort saveCohort(Cohort cohort) {
		return cohortRepo.save(cohort);
	}

	@Override
	public List<Cohort> findAllByTrainerId(int id) {
		return cohortRepo.findByTrainerUserId(id);
	}
	
	@Override
	public Cohort findOneByCohortId(int id) {
		return cohortRepo.findOneByCohortId(id);
	}

	@Override
	public List<Cohort> findAll() {
		return cohortRepo.findAll();
	}


}
