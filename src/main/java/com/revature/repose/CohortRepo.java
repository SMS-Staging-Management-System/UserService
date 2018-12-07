package com.revature.repose;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Cohort;

@Repository
public interface CohortRepo extends JpaRepository<Cohort,Integer> {

}
