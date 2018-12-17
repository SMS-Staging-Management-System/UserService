package com.revature.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.revature.dto.CohortUserListInputDto;
import com.revature.dto.CohortUserListOutputDto;
import com.revature.models.Cohort;

@Service
public interface CohortService {
	CohortService currentImplementation = new CohortServiceImpl();

	Cohort saveCohort(Cohort cohort);

	List<Cohort> findAllByTrainerId(int id);


	Cohort findOneByCohortId(int id);

	List<Cohort> findAll();

	CohortUserListOutputDto saveCohortWithUserList(CohortUserListInputDto cuList, HttpServletRequest req) throws IOException;


	
}
