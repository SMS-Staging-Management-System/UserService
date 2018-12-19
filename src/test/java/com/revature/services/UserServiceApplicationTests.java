package com.revature.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.Cohort;
import com.revature.models.User;
import com.revature.repos.UserRepo;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {
	private static Logger log = Logger.getRootLogger();

	@Mock
	public UserRepo userRepo;

	@InjectMocks
	public UserServiceImpl userService;

	@BeforeClass
	public static void beforeAll() {
		log.debug("User service starting");
	}

	@Test
	public void findOneById() {
//		User testUser = new User(1,"James","Dula","jDula@gmail.com",new HashSet<Cohort>());
		User testUser1 = new User(2, "James", "Dula", "jDula@gmail.com", new HashSet<Cohort>());

		when(userRepo.findOneByUserId(1)).thenReturn(testUser1);

		User calledUser = userService.findOneById(1);

		Assert.assertEquals(testUser1, calledUser);

	}

	@Test
	public void findAll() {
		User testUser = new User(1, "James", "Dula", "jDula@gmail.com", new HashSet<Cohort>());
		User testUser1 = new User(2, "An", "Ta", "jDula@gmail.com", new HashSet<Cohort>());

		List<User> userList = new ArrayList<User>();
		userList.add(testUser);
		userList.add(testUser1);

		when(userRepo.findAll()).thenReturn(userList);

		List<User> calledUser = userService.findAll();

		Assert.assertEquals(userList, calledUser);

	}

	@AfterClass
	public static void afterAll() {
		log.debug("User service is complete");
	}
}