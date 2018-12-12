package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Cohort;

@Repository
public interface CohortRepo extends JpaRepository<Cohort,Integer> {

	List<Cohort> findByTeacherUserId(int id);

}
