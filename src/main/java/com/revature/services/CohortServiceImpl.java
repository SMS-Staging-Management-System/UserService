package com.revature.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.CohortUserListInputDto;
import com.revature.dto.CohortUserListOutputDto;
import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.repos.CohortRepo;
import com.revature.utils.CognitoUtil;

@Service
public class CohortServiceImpl implements CohortService{

	@Autowired
	CohortRepo cohortRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CognitoUtil cognitoUtil;
	
	
	

	Logger log = Logger.getRootLogger();
	
	public Cohort saveCohort(Cohort cohort) {
		if (cohortRepo.findOneByCohortName(cohort.getCohortName()) == null){
			return cohortRepo.save(cohort);
		}
		return null;
	}

	
	
	@Override
	public List<Cohort> findAllByTrainerId(int id) {
		return cohortRepo.findByTrainerUserId(id);
	}
	
	@Override
	public Cohort findOneByCohortId(int id) {
		return cohortRepo.findOneByCohortId(id);
	}

	@Override
	public List<Cohort> findAll() {
		return cohortRepo.findAll();
	}



	@Override
	public CohortUserListOutputDto saveCohortWithUserList(CohortUserListInputDto cuList, HttpServletRequest req) throws IOException {
		User trainer = userService.findOneByEmail(cognitoUtil.extractTokenEmail());

		log.info("\n Trainer is: " + trainer);

		CohortUserListOutputDto cuListOutput = new CohortUserListOutputDto();
		Cohort cohort = new Cohort(cuList.getCohortName(), cuList.getCohortDescription(), trainer);

		log.info("Cohort is: " + cohort);
		Cohort savedCohort = saveCohort(cohort);
		cuListOutput.setCohort(savedCohort);

		if (cuListOutput.getCohort() == null) {
			cuListOutput.setMessages("Cohort could not be created or already exists, all users rejected");
			return cuListOutput;
		}

		if (cuList.getUserList() == null) {
			cuListOutput.setMessages("Created cohort with no users");
			return cuListOutput;
		}

		List<User> users = cuList.toUsersList(savedCohort);

		for (User user : users) {
			User tempUser = cognitoUtil.registerUser(user, req);
			if (tempUser != null)
				cuListOutput.getAcceptedUsers().add(tempUser);
			else
				cuListOutput.getRejectedUsers().add(user);
		}
		
		cuListOutput.setMessages("Created Cohort with users");
		log.info("Accepted Users: " + cuListOutput.getAcceptedUsers());
		log.info("Rejected Users: " + cuListOutput.getRejectedUsers());
		return cuListOutput;
		
	}


}
