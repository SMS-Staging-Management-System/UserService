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

import com.revature.models.StatusHistory;
import com.revature.services.StatusHistoryService;

@RunWith(MockitoJUnitRunner.class)
public class StatusHistoryControllerTest {

	@InjectMocks
	StatusHistoryController tester = new StatusHistoryController();
	
	@Mock
	private StatusHistoryService statusHistoryService;
	
	@Test
	public void testFindByUser() {
		StatusHistory testStatus = new StatusHistory();
		List<StatusHistory> statusHistories = new ArrayList<StatusHistory>();
		statusHistories.add(testStatus);
		
		when(statusHistoryService.findByUser(0)).thenReturn(statusHistories);

		List<StatusHistory> result = tester.findByUser(0);

		Assert.assertEquals("Check find by id", statusHistories, result);
	}

//	@Test
//	public void testSaveStatusHistory() {
//		fail("Not yet implemented");
//	}

}
