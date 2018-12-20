package com.revature.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.revature.models.Cohort;
import com.revature.models.User;

@Component
public class ResponseEntityUtil {
	
	public ResponseEntity<User> getResponseEntity(User user){
		return user == null ?  
				 new ResponseEntity<User>(HttpStatus.NO_CONTENT) :
					 new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	public ResponseEntity<User> getResponseEntitySaveUser(User user){
		return user == null ?  
				 new ResponseEntity<User>(HttpStatus.CONFLICT) :
					 new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	public ResponseEntity<List<User>> getResponseEntityUserList(List<User> userList){
		return userList == null || userList.size() == 0 ?  
				 new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT) :
					 new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	
	public ResponseEntity<Cohort> getResponseEntity(Cohort cohort){
		return cohort == null ?  
				 new ResponseEntity<Cohort>(HttpStatus.NO_CONTENT) :
					 new ResponseEntity<Cohort>(cohort,HttpStatus.OK);
	}
	
	public ResponseEntity<List<Cohort>> getResponseEntityCohortList(List<Cohort> cohortList){
		return cohortList == null || cohortList.size() == 0 ?  
				 new ResponseEntity<List<Cohort>>(HttpStatus.NO_CONTENT) :
					 new ResponseEntity<List<Cohort>>(cohortList,HttpStatus.OK);

	}
	
	
	
	
}
