package com.revature.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Cohort;

public interface CohortRepo extends JpaRepository<Cohort, Integer> {
	List<Cohort> findByTrainerUserId(int trainerId);
	
	Cohort findByCohortToken(String cohortToken);
	List<Cohort> findByEndDateBetween(LocalDate begin, LocalDate end);
}
