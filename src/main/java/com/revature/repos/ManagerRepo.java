package com.revature.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.models.Managers;

@Repository 
public interface ManagerRepo extends JpaRepository<Managers, Integer> {
	@Query("FROM Managers managers WHERE address IN (SELECT addressId FROM Address address WHERE address.alias = :alias)")
	public List<Managers> findByAddress(@Param("alias") String alias);
}