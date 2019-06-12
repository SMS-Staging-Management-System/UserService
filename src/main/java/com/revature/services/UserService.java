package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.User;

@Service
public interface UserService {
	//
	// public List<User> findAll();
	//
	 public User findOneById(int id);
	//
	 public List<User> findAllByCohortId(int id);
	//
	 public User saveUser(User u);
	//
	 public User updateProfile(User u);
	//
	 public User findOneByEmail(String email);
	 
	//
	// public CohortUserListOutputDto saveUsers(UserListInputDto userList, int id)
	// throws IOException;
}
