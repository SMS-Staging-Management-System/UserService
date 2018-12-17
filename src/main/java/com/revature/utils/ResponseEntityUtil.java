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
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.PARTIAL_CONTENT);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	public ResponseEntity<List<User>> getResponseEntityUserList(List<User> userList){
		if (userList == null) {
			return new ResponseEntity<List<User>>(HttpStatus.PARTIAL_CONTENT);
		}
		if (userList.size() == 0) {
			return new ResponseEntity<List<User>>(HttpStatus.PARTIAL_CONTENT);
		}
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	
	public ResponseEntity<Cohort> getResponseEntity(Cohort cohort){
		if (cohort == null) {
			return new ResponseEntity<Cohort>(HttpStatus.PARTIAL_CONTENT);
		}
		return new ResponseEntity<Cohort>(cohort,HttpStatus.OK);
	}
	
	public ResponseEntity<List<Cohort>> getResponseEntityCohortList(List<Cohort> cohortList){
		if (cohortList == null) {
			return new ResponseEntity<List<Cohort>>(HttpStatus.PARTIAL_CONTENT);
		}
		if (cohortList.size() == 0) {
			return new ResponseEntity<List<Cohort>>(HttpStatus.PARTIAL_CONTENT);
		}
		return new ResponseEntity<List<Cohort>>(cohortList,HttpStatus.OK);
	}
	
	
	
	
}
