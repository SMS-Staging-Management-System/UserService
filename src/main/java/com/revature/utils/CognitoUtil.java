package com.revature.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.CognitoAuthResponse;
import com.revature.dto.CognitoRegisterResponse;
import com.revature.models.User;
import com.revature.services.UserService;

@Component
public class CognitoUtil {

	private String tokenEmail;
	private String token;

	@Autowired
	private CognitoRestTemplate cognitoRestTemplate;

	@Autowired
	private UserService userService;

	/**
	 * Registers a User with Cognito
	 * 
	 * @param String email
	 * @return Response
	 * @throws IOException
	 */
	public User registerUser(User user, HttpServletRequest req) throws IOException {

		String cognitoToken = req.getHeader("Authentication");
		if (userService.findOneByEmail(user.getEmail()) == null) {
			ResponseEntity<String> response = cognitoRestTemplate.registerUser(user.getEmail());
			System.out.println(response);
			if (response.getStatusCodeValue() == HttpStatus.SC_OK) {
				ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);
				JsonNode obj = mapper.readTree(response.getBody());
				CognitoRegisterResponse registerModel = mapper.treeToValue(obj.get("User"),
						CognitoRegisterResponse.class);

				return userService.saveUser(user);				
			}
		}
		
		return null;
	}

	/**
	 * Verify Cognito is Authenticated
	 * 
	 * @param req // * @return List<String>
	 * @throws IOException
	 */
	public List<String> cognitoAuth(HttpServletRequest req) throws IOException {
		// "Authorization" : "Bearer tokenValue"1
		String cognitoToken = req.getHeader("Authentication");
		ResponseEntity<String> response = cognitoRestTemplate.checkAuth(cognitoToken);

		List<String> authRoleList = new ArrayList<String>();

		if (response.getStatusCodeValue() == HttpStatus.SC_OK) {
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);

			CognitoAuthResponse authModel = mapper.treeToValue(mapper.readTree(response.getBody()),
					CognitoAuthResponse.class);
			tokenEmail = authModel.getEmail();
			System.out.println("email" + tokenEmail);
			token = cognitoToken;

			if (authModel.getCognitoGroups() != null) {
				if (authModel.getCognitoGroups().length() == 1) {
					authRoleList.add(authModel.getCognitoGroups());

				} else {
					authRoleList = Arrays.asList(authModel.getCognitoGroups().split(","));
				}

				System.out.println(authRoleList.toString());
				return authRoleList;
			}
			authRoleList.add("users");
			return authRoleList;
		}
		return null;
	}

	/**
	 * Returns email associated with token. // * @return String
	 */
	public String extractTokenEmail() {
		return tokenEmail;
	}

	/**
	 * Returns email associated with token. // * @return String
	 */
	public String extractToken() {
		return token;
	}

}
