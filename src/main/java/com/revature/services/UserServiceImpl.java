package com.revature.services;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.CohortUserListOutputDto;
import com.revature.dto.UserListInputDto;
import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.repos.UserRepo;
import com.revature.utils.CognitoUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo userRepo;

	@Autowired
	CognitoUtil cognitoUtil;

	@Autowired
	CohortService cohortService;

	@Autowired
	CognitoUtil cUtil;

	Logger log = Logger.getRootLogger();

	@Override
	public List<User> findAll() {
		return userRepo.findAll();

	}

	@Override
	public User findOneById(int id) {
		return userRepo.findOneByUserId(id);

	}

	@Override
	public List<User> findAllByCohortId(int id) {
		return userRepo.findAllByCohortsCohortId(id);

	}

	@Override
	public User saveUser(User u) {
		return userRepo.save(u);
	}

	@Override
	public User findOneByEmail(String email) {
		return userRepo.findByEmailIgnoreCase(email);
	}

	// Needs more verification before an update occurs.
	@Override
	public User updateProfile(User u) {
		User tempAppUser = userRepo.findByEmailIgnoreCase(cognitoUtil.extractTokenEmail());
		if (tempAppUser == null) {
			log.info("User cannot be found.");
			return null;
		}
		if (u.getFirstName() != null) {
			tempAppUser.setFirstName(u.getFirstName());
		}
		if (u.getLastName() != null) {
			tempAppUser.setLastName(u.getLastName());
		}
//	 	Email doesn't get updated with cognito so we
//		have to fix that before we can change our email
//		if (u.getEmail() != null) {
//			tempAppUser.setEmail(u.getEmail());
//		}
		if (u.getPhoneNumber() != null) {
			tempAppUser.setPhoneNumber(u.getPhoneNumber());
		}
		if (u.getCountry() != null) {
			tempAppUser.setCountry(u.getCountry());
		}
		if (u.getTimezone() != null) {
			tempAppUser.setTimezone(u.getTimezone());
		}
		if (u.getZipCode() != null) {
			tempAppUser.setZipCode(u.getZipCode());
		}
		if (u.getCity() != null) {
			tempAppUser.setCity(u.getCity());
		}
		if (u.getState() != null) {
			tempAppUser.setState(u.getState());
		}
		userRepo.save(tempAppUser);
		return tempAppUser;
	}

	public User userInfo() {
		String email = cUtil.extractTokenEmail();
		return userRepo.findByEmailIgnoreCase(email);
	}

	@Override
	public CohortUserListOutputDto saveUsers(UserListInputDto userList, int id) throws IOException {
		log.info(userList);
		Cohort cohort = cohortService.findOneByCohortId(id);
		List<User> users = userList.toUsersList(cohort);

		CohortUserListOutputDto cuListOutput = new CohortUserListOutputDto();
		cuListOutput.setCohort(cohort);
		if (cohort != null) {
			for (User user : users) {
				User tempUser = cognitoUtil.registerUser(user);
				if (tempUser != null)
					cuListOutput.getAcceptedUsers().add(tempUser);
				else
					cuListOutput.getRejectedUsers().add(user);
			}
			cuListOutput.setMessages("Returned Accepted Users and Rejected Users");
		} else {
			cuListOutput.setMessages("Cohort could not be found");
			cuListOutput.setRejectedUsers(users);
		}
		return cuListOutput;
	}

}
