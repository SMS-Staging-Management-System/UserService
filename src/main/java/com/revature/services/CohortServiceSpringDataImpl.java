package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.repose.CohortRepo;

@Service
public class CohortServiceSpringDataImpl implements CohortService{

	@Autowired
	CohortRepo cohortRepo;
	
	public String saveCohort(Cohort cohort) {
		return cohortRepo.save(cohort).getCohortToken();
	}

//	@Override
//	public Cohort saveUser(User user, int cohortId) {
//		Cohort tempCohort = cohortRepo.findById(cohortId).get();
//		System.out.println(tempCohort);
//		Set<User> users = tempCohort.getUsers();
//		users.add(user);
//		tempCohort.setUsers(users);
//		System.out.println(tempCohort.getUsers());
//		cohortRepo.save(tempCohort);
//		return tempCohort;
//	}

}
