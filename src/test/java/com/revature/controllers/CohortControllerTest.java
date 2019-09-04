package com.revature.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.revature.models.Address;
import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.repos.CohortRepo;
import com.revature.repos.UserRepo;
import com.revature.services.CohortService;

@RunWith(MockitoJUnitRunner.class)
public class CohortControllerTest {

	@InjectMocks
	CohortController cohortControllerTester;
	
	@Mock
	CohortService cohortService;
	
	@Mock
	CohortRepo cohortRepo;
	
	@Mock
	UserRepo userRepo;
	
	@Mock
	Page<Cohort> cohortsPage;
	
	@Test
	public void testFindByTrainer() {
//		Create a cohort stub
		Cohort cohort = new Cohort();
//		Create a list with that cohort on it
		List<Cohort> cohorts = new ArrayList<>();
		cohorts.add(cohort);
//		Create fake id
		int fakeTrainerId = (int) (Math.random() * 100);
//		Define what the service method should return when passing the trainer id
		when(cohortService.findByTrainer(fakeTrainerId)).thenReturn(cohorts);
//		Create assertion
		assertThat(cohortControllerTester.findByTrainer(fakeTrainerId)).isEqualTo(cohorts);
	}

	@Test
	public void testFindAllByPage() {
//		Create stub for a list of cohorts
		List<Cohort> cohortsList = new ArrayList<>();
//		Create a stub for the pageable parameter on the cohortsService.findAllByPage() method
		Pageable pageable = PageRequest.of(1, 7, Sort.by("cohortId"));
//		Define what each method should return
		when(cohortsPage.getContent()).thenReturn(cohortsList);
		when(cohortService.findAllByPage(pageable)).thenReturn(cohortsPage);
//		Call the findAll method from the cohortController mock
		Page<Cohort> result = cohortControllerTester.findAll(1);
		List<Cohort> resultCohort = result.getContent();
//		Write assertion
		assertThat(cohortsList).isEqualTo(resultCohort);
	}

	@Test
	public void testFindCohortUsers() {
//		Create a cohort stub
		Cohort fakeCohort = new Cohort();
//		Create a fake id for the cohort
		fakeCohort.setCohortId((int) (Math.random() * 100)); 
//		Create a fake list of users that are supposed to be part of the cohort
		Set<User> fakeUsersInCohort = new HashSet<>();
//		Add a new fake user and add it to the Set
		User fakeUser = new User(1, "Test", "User", "some_mail@gmail.com", null,
								 null, null, null, null);
		fakeUsersInCohort.add(fakeUser);
//		Define that the cohort has this users
		fakeCohort.setUsers(fakeUsersInCohort);
//		Define what the service method should return
		when(cohortService.findCohortUsers(fakeCohort.getCohortId())).thenReturn(fakeUsersInCohort);
//		Write assertion
		assertThat(cohortControllerTester.findCohortUsers(fakeCohort.getCohortId()))
			.isEqualTo(fakeUsersInCohort);
	}

	@Test
	public void testFindCohortByToken() {
//		Create cohort stub
		Cohort fakeCohort = new Cohort();
//		Create fake cohort token
		String fakeCohortToken = "" + (Math.random() * 100);
//		Assign id and token to fake cohort
		fakeCohort.setCohortId((int) Math.random()*100);
		fakeCohort.setCohortToken(fakeCohortToken);
//		Define what the cohortService should return
		when(cohortService.findCohortByToken(fakeCohortToken)).thenReturn(fakeCohort);
//		Write assertion
		assertThat(cohortControllerTester.findCohortByToken(fakeCohortToken))
			.isEqualTo(fakeCohort);
	}
	
	@Test
	public void testJoinCohort() {
//		Create fake user to join the fake cohort
		User fakeUser = new User();
		fakeUser.setEmail("fake@mail.com");
//		Create fake cohort that the user is supposed to join
		Cohort fakeCohortToJoin = new Cohort();
//		Create fake cohortToken for the token the user is going to join
		fakeCohortToJoin.setCohortToken("" + (Math.random() * 100));
//		Assign address
		Address fakeCohortAddress = new Address(1, "Alias", null, null, 
												null, null, null, true);	
		fakeCohortToJoin.setAddress(fakeCohortAddress);
//		Define what should be returned from cohortService.joinCohort()
		when(cohortService.joinCohort(fakeUser, fakeCohortToJoin.getCohortToken()))
			.thenReturn("OK");
//		Write assertion
		assertThat(cohortControllerTester.joinCohort(fakeUser, fakeCohortToJoin.getCohortToken()))
			.isEqualTo(new ResponseEntity<String>(HttpStatus.OK));
	}

	@Test
	public void testSaveCohort() {
//		Create fake cohort
		Cohort fakeCohort = new Cohort();
//		Write what the save method should return
		when(cohortService.save(fakeCohort)).thenReturn(fakeCohort);
//		Assert save cohort method
		assertThat(cohortControllerTester.save(fakeCohort)).isEqualTo(fakeCohort);
	}
	
	@Test
	public void testFindPreStagingCohorts() {
//		Create fake epochDate
		long epochDate = 123347L;
//		Create fake start and ending dates
		LocalDate date = Instant.ofEpochMilli(epochDate).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate begin = date;
		LocalDate end = date.plusDays(14);
//		Create fake cohorts list
		List<Cohort> fakeCohorts = new ArrayList<>();
		Cohort fakeCohort = new Cohort(1, "Test Cohort", null, null, null,
									   begin, end, null, null);
		fakeCohorts.add(fakeCohort);
//		Define what the findEndingCohorts() method should return
		when(cohortService.findEndingCohorts(date)).thenReturn(fakeCohorts);
//   	Write assertion
  		assertThat(cohortControllerTester.findPreStagingCohorts(epochDate)).isEqualTo(fakeCohorts);
	}
	
}
