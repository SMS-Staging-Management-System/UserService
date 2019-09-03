package com.revature.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.repos.CohortRepo;
import com.revature.repos.UserRepo;

@Service
public class CohortServiceImpl implements CohortService {
	@Autowired
	private CohortRepo cohortRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public Set<User> findCohortUsers(int id){
		Cohort cohort = cohortRepo.getOne(id);
		return cohort.getUsers();
	}
	
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

	@Override
	public Page<Cohort> findAllByPage(Pageable pageable) {
		return cohortRepo.findAll(pageable);
	}
	
	@Override 
//	Added get users in cohort in order to attatch the list of users and avoid users
//	to re-join to the cohort.
	public Cohort findCohortByToken(String token) {
//		First, get the cohort from the repo
		Cohort foundCohort = cohortRepo.findByCohortToken(token);
//		Avoid NullPointerException
		if (foundCohort != null) {
//			According to the cohort id, find the members of it
			int cohortId = foundCohort.getCohortId();
//			Assign the users to the found cohort
			Set<User> users = findCohortUsers(cohortId);
			foundCohort.setUsers(users);
			return foundCohort;			
		} else {
			return null;
		}
	}

  	@Override
	@Transactional
	public String joinCohort(User user, String cohortToken) {
		Cohort cohort = cohortRepo.findByCohortToken(cohortToken);
		User nUser = userRepo.findByEmailIgnoreCase(user.getEmail());
		if(cohort == null) {
			return "Not Found";
		} else if(nUser == null){
			return "Bad Request";
		} else {
			try {
				//Set<User> nUsers = cohort.getUsers();
				//nUsers.add(user);
				//cohort.setUsers(nUsers);
				Set<Cohort> nCohorts = nUser.getCohorts();
				nCohorts.add(cohort);
				nUser.setCohorts(nCohorts);
				nUser.setTrainingAddress(cohort.getAddress());
				userRepo.save(nUser);
				//cohortRepo.save(cohort);
				return "OK";
			} catch (Exception e) {
				return "Internal Server Error";
			}
		}
	}
  	
  	@Override
  	public List<Cohort> findEndingCohorts(LocalDate date) {
		
		LocalDate begin = date;
		LocalDate end = date.plusDays(14);
		
  		return cohortRepo.findByEndDateBetween(begin, end);
  	}

}
