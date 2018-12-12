package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Cohort;
import com.revature.repose.CohortRepo;

@Service
public class CohortServiceSpringDataImpl implements CohortService{

	@Autowired
	CohortRepo cohortRepo;
	
	public Cohort saveCohort(Cohort cohort) {
		return cohortRepo.save(cohort);
	}

	@Override
	public List<Cohort> findAllByUserId(int id) {
		return cohortRepo.findAllByTeacherUserId(id);
	}

}
