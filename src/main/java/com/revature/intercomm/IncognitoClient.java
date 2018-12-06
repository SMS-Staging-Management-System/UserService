package com.revature.intercomm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.Response;

@FeignClient(url="https://t4o3pxu8dj.execute-api.us-west-2.amazonaws.com")
public interface IncognitoClient {
	
	@PostMapping("/dev/cognito/users")
	@Headers("Content-Type: application/json")
    @Body("%7B\n" +
            "  \"email\": \"{email}\"\n" +
            "%7D")
	Response registerUser(@Param("email") String email);
	
	
	
	@GetMapping("/dev/auth")
	@Headers("Authorization: \"{token}\"")
	Response verifyIncognito(@Param("token") String token);
}
