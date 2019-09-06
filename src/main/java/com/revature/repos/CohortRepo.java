package com.revature.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Cohort;

public interface CohortRepo extends JpaRepository<Cohort, Integer> {
	List<Cohort> findByTrainerUserId(int trainerId);

	Cohort findByCohortToken(String cohortToken);
	List<Cohort> findByEndDateBetween(LocalDate begin, LocalDate end);

	Page<Cohort> findAllByAddressAddressId(int addressId, Pageable page);

	Page<Cohort> findAllByTrainerUserId(int userId, Pageable page);

	Page<Cohort> findAllByTrainerEmail(String email, Pageable page);

}
