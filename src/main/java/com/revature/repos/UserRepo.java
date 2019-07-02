package com.revature.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Page<User> findAll(Pageable pageable);

	public List<User> findAllByCohortsCohortId(int id);

	public User findByEmailIgnoreCase(String email);

	// The following Query method is specifically added for the
	// findUserByEmail method in UserController method that handels
	// path = "email/partial'(ss)
	@Query("FROM User user WHERE user.email LIKE %:email%")
	public Page<User> findUsersByEmailIgnoreCase(@Param("email") String email, Pageable pageable);

	@Query("FROM User user WHERE LOWER(user.email) IN :emailList")
	public Page<User> findAllUserByEmailIgnoreCase(@Param("emailList") List<String> emailList, Pageable pageable);

	@Query("FROM User user WHERE LOWER(user.email) IN :emailList")
	public List<User> findAllUserByEmailIgnoreCase(@Param("emailList") List<String> emailList);

	@Query("FROM User user WHERE LOWER(user.email) IN :emailList")
	public List<User> findAllUserByEmailIgnoreCaseNotPageable(@Param("emailList") List<String> emailList);
	
	@Query("FROM User WHERE userStatus = 9")
	public List<User> findByStatus();
	
}
