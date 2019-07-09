package com.revature.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revature.models.User;

@Service
public interface UserService {
	//
	 public Page<User> findAll(Pageable pageable);
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
	 
	 public User findByEmail(String email);
	//
	 //This method is added for use in findUserByEmail() method
	 //In UserController class that handles path = "email/partial" (ss)
	 public Page<User> findUserByPartialEmail(String email, Pageable pageable);
	//
	 public Page<User> findListByEmail(List<String> emailList, Pageable pageable);
	 
	 public List<User> findListByEmailNotPageable(List<String> emailList);
  
	 public List<User> findListByEmail(List<String> emailList);
	 
	 public List<User> findByStatus();
	//
	// public CohortUserListOutputDto saveUsers(UserListInputDto userList, int id)
	// throws IOException;

	 
	public List<User> findAllInStaging();
	
	//method to get all the dropped users from last week
	public List<User> findAllDroppedAssociate();
	
	public Page<User> findAllDroppedAssociate(Pageable pageable);
}
