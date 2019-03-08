package com.revature.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Cohort;
import com.revature.repos.CohortRepo;

@Service
public class CohortServiceImpl implements CohortService {
	@Autowired
	private CohortRepo cohortRepo;

	@Override
	public List<Cohort> findByTrainer(int trainerId) {
		return cohortRepo.findByTrainerUserId(trainerId);
	}

	@Override
	public Cohort save(Cohort cohort) {
		cohort.setCohortToken(UUID.randomUUID().toString());
		cohort.setCohortId(0);
		cohortRepo.saveAndFlush(cohort);
		return cohort;
	}

	@Override
	public List<Cohort> findAll() {
		return cohortRepo.findAll();
	}

}
