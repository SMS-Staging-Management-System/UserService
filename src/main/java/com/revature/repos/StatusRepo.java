package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Status;

public interface StatusRepo extends JpaRepository<Status, Integer> {
}
