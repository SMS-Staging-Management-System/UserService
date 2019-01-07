package com.revature.repos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public List<User> findAllByCohortsCohortId(int id);

	public User findByEmailIgnoreCase(String email);

}
