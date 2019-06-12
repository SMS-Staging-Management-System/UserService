package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Cohort;

@Repository
public interface SortingRepo extends JpaRepository<Cohort, Integer>{
	
	
	@Query("FROM Cohort WHERE Lower(cohortName) LIKE %:cohortName%")
	List<Cohort> findByCohortName(String cohortName);
	
	@Query("FROM Cohort c WHERE Lower(c.address.alias) LIKE %:alias%")
	List<Cohort> findByAddressAlias(String alias);
	
	
}
