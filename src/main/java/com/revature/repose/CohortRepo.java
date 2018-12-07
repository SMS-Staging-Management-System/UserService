package com.revature.repose;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Cohort;
import com.revature.models.User;

@Repository
public interface CohortRepo extends JpaRepository<Cohort,Integer> {
	
}
