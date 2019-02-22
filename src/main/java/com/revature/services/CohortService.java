package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Cohort;

@Service
public interface CohortService {

	List<Cohort> findByTrainer(int trainerId);

	Cohort save(Cohort cohort);
}
