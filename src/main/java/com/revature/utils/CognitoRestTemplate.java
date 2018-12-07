package com.revature.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.revature.models.CognitoResponse;

@Component
public class CognitoRestTemplate {
	
	final String baseUrl = "https://t4o3pxu8dj.execute-api.us-west-2.amazonaws.com/";
	final String registerUrl = "dev/cognito/users";
	final String authUrl = "dev/auth";
	
	
	public  ResponseEntity<String> registerUser(String email) {
//		RestTemplate rt = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		String url= baseUrl + registerUrl;
//		String requestJson = "{\"email\":\"" + email + "\"}";
//		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
//		
//		String response = "";
//		try{
//			response = rt.postForObject(url,entity , String.class );
//			System.out.println(response);
//			return true;
//			
//		}catch(HttpClientErrorException e) {
//			System.out.print(e);
//			return false;			
//		}
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url= baseUrl + registerUrl;
		String requestJson = "{\"email\":\"" + email + "\"}";
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		
		try{
			return rt.exchange(url ,HttpMethod.POST, entity , String.class );
			
			
		}catch(HttpClientErrorException e) {
			System.out.print(e);
			return null;			
		}
	}
	
	public boolean checkAuth(String token) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		String requestJson = "";
		String url= baseUrl + authUrl;
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		try{
			ResponseEntity<String> response = rt.exchange(url,HttpMethod.GET ,entity , String.class );
			System.out.println(response);
			return true;
			
		}catch(HttpClientErrorException e) {
			System.out.print(e);
			return false;			
		}
	}
	
	
	
}
