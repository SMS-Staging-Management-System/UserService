package com.revature.feign;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;


public class FeignExceptionTest {

	FeignException tester = new FeignException();
	@Test
	public void testFeignException() {
		
		final int randStatus = (int) (Math.random() * 100);
		
		final String randReason = "" + Math.random() * 100;
		
		tester = new FeignException(randStatus, randReason);
		
		final int testStatus = tester.getStatus();
		final String testReason = tester.getReason();
		
		assertEquals("check FeignException paramaterized constructor",randStatus, testStatus);
		assertEquals("check FeignException paramaterized constructor", randReason , testReason);
	}

	@Test
	public void testGetStatus() {
		final int randStatus = (int) (Math.random() * 100);

		tester = new FeignException();
		tester.setStatus(randStatus);
		
		try {
			Field f = tester.getClass().getDeclaredField("status");
			f.setAccessible(true);
			final int testStatus = (int)f.get(tester);
			
			assertEquals("check getStatus",testStatus, tester.getStatus());
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
public void testSetStatus() {

		final int initialRandStatus = (int) (Math.random() * 100);
		final int updatedRandStatus = (int) (Math.random() * 100);

		tester = new FeignException();
		try {
			Field f = tester.getClass().getDeclaredField("status");
			f.setAccessible(true);
			f.setInt(tester, initialRandStatus);
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("check set status", initialRandStatus, tester.getStatus());
		
		tester.setStatus(updatedRandStatus);

		final int testRandStatus = tester.getStatus();
		
		assertEquals("check set status", updatedRandStatus, testRandStatus);
	}

	@Test
	public void testGetReason() {
		tester = new FeignException();
		final String randReason = "" + (Math.random() * 100);

		tester.setReason(randReason);

		Field f;
		try {
			f = tester.getClass().getDeclaredField("reason");
			f.setAccessible(true);

			final String testReason = (String)f.get(tester);

		    assertEquals("check getStatus",testReason, tester.getReason());
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Test
	public void testSetReason() {
		final String randReason = "" + (Math.random() * 100);
		
		tester = new FeignException();
		tester.setReason(randReason);

		try {
			Field f = tester.getClass().getDeclaredField("reason");
			f.setAccessible(true);
			final String testReason= (String)f.get(tester);
			
			assertEquals("check getStatus",testReason, tester.getReason());
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
