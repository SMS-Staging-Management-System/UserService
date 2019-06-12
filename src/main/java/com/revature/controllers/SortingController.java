package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Cohort;
import com.revature.services.SortingService;

@RestController
@RequestMapping(value = "page")

public class SortingController {

	@Autowired
	private SortingService sortingService;
	
	@GetMapping()
	public Page<Cohort> findAllCohorts(){
		Pageable arbitrary = PageRequest.of(0, 1);
		return sortingService.findAllCohorts(arbitrary);
	}
	
	@GetMapping("/{cohortName}")
	public List<Cohort> findByCohortName(@PathVariable String cohortName){
		return sortingService.findByCohortName(cohortName);
	}
	
	@GetMapping("/address/{alias}")
	public List<Cohort> findByAddressAddressAlias(@PathVariable String alias) {
		return sortingService.findByAddressAddressAlias(alias);
	}
}
