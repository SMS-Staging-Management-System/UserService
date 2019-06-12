package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revature.models.Cohort;
import com.revature.repos.SortingRepo;

@Service
public class SortingService {
	
	@Autowired
	private SortingRepo sortingRepo;

	Pageable arbitrary = PageRequest.of(0, 3);
	
	/*
	 * Pageable sortedByName = PageRequest.of(0, 3, Sort.by("cohortName"));
	 */
	public Page<Cohort> findAllCohorts(Pageable arbitrary){
		return sortingRepo.findAll(arbitrary);
	}
	public List<Cohort> findByCohortName(String cohortName){
		String passing = cohortName.toLowerCase();
		return sortingRepo.findByCohortName(passing);
	}
	public List<Cohort> findByAddressAddressAlias(String alias) {
		String passedval = alias.toLowerCase();
		return sortingRepo.findByAddressAlias(passedval);
	}
}

