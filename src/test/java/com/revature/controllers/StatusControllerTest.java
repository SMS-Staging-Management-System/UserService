package com.revature.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.models.Status;
import com.revature.services.StatusService;



@RunWith(MockitoJUnitRunner.class)
public class StatusControllerTest {
	
	@InjectMocks
	StatusController tester = new StatusController();
	
	@Mock
	private StatusService statusService;
	
	@Test
	public void testFindAll() {
		Status statusForTesting = new Status();
		List<Status> statuses = new ArrayList<Status>();
		statuses.add(statusForTesting);
		
		when(statusService.findAll()).thenReturn(statuses);
		
		List<Status> result = tester.findAll();
		
		Assert.assertEquals("Check find all", statuses, result);
	}

}
