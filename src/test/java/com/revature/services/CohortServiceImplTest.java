package com.revature.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.repos.CohortRepo;
import com.revature.repos.UserRepo;

@RunWith(MockitoJUnitRunner.class)
public class CohortServiceImplTest {
	
	@InjectMocks
	private CohortServiceImpl cohortServiceTest;
	
	@Mock
	CohortRepo cohortRepo;
	
	@Mock
	UserRepo userRepo;
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void findCohortByTokenTest() {
//		Create a cohort stub
		Cohort cohortStub = new Cohort();
//		Define the cohort stub id
		cohortStub.setCohortId(1);
//		Token stub
		String tokenStub = "" + Math.random() * 100;
		cohortStub.setCohortToken(tokenStub);
//		Then mock the required cohortRepo methods that are supposed to be called in the
//		service class
		Mockito.when(cohortRepo.findByCohortToken(tokenStub)).thenReturn(cohortStub);
		Mockito.when(cohortRepo.getOne(cohortStub.getCohortId())).thenReturn(cohortStub);
//		Check found cohort is not null
		assertThat(cohortServiceTest.findCohortByToken(tokenStub)).isNotNull();
	}
	
	@Test
	public void joinCohortTest() {
//		Create a cohort stub to mock the cohortRepo.findByCohortToken()
		Cohort cohortStub = new Cohort();
//		Define the cohort stub id
		cohortStub.setCohortId(1);
//		Token stub
		String tokenStub = "" + Math.random() * 100;
//		Define the mock for findByCohortToken
		Mockito.when(cohortRepo.findByCohortToken(tokenStub)).thenReturn(cohortStub);
//		Create a user stub to mock the userRepo.findByEmailIgnoreCase()
		User userStub = new User();
//		Create a fake email
		String fakeEmail = "fake@mail.com";
//		Assign the email to the user stub
		userStub.setEmail(fakeEmail);
		Mockito.when(userRepo.findByEmailIgnoreCase(fakeEmail)).thenReturn(userStub);
		assertThat(cohortServiceTest.joinCohort(userStub, tokenStub)).isEqualTo("OK");
	}
	
//	Test that the cohort is being saved without returning null
	@Test
	public void saveTest() {
		assertThat(cohortServiceTest.save(new Cohort())).isNotNull();
	}
 
}