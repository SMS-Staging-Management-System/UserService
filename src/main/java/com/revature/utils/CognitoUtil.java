package com.revature.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.CognitoAuthResponse;
import com.revature.models.User;
import com.revature.services.UserService;


@Component
public class CognitoUtil {

	
	@Value("${spring.profiles}")
	private String stage;
	
	private Logger logger = Logger.getRootLogger();
	
	@Autowired
	private CognitoRestTemplate cognitoRestTemplate;

	Logger log = Logger.getRootLogger();
	
	
	@Autowired
	private UserService userService;

	/**
	 * Registers a User with Cognito
	 * 
	 * @param String email
	 * @return Response
	 * @throws IOException
	 */
	public User registerUser(User user) throws IOException {
		if (userService.findOneByEmail(user.getEmail()) == null) {
			ResponseEntity<String> response = cognitoRestTemplate.registerUser(user.getEmail());
			if (response.getStatusCodeValue() == HttpStatus.SC_OK) {
				ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);
				JsonNode obj = mapper.readTree(response.getBody());
        
//				// Response Object of Cognito Response.
//				CognitoRegisterResponse registerModel = mapper.treeToValue(obj.get("User"), CognitoRegisterResponse.class);
				
        return userService.saveUser(user);				
			}
			
			log.info("Email " + user.getEmail() + " could not be registered with cognito.");
			return null;
		}
		
		log.info("Email " + user.getEmail() + " already registered with database");
		return null;
	}

	/**
	 * Verify Cognito is Authenticated
	 * 
	 * @param req // * @return List<String>
	 * @throws IOException
	 */
	public List<String> cognitoAuth() throws IOException {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// "Authorization" : "Bearer tokenValue"1
		String cognitoToken = req.getHeader("Authentication");
		ResponseEntity<String> response = cognitoRestTemplate.checkAuth(cognitoToken);

		List<String> authRoleList = new ArrayList<String>();

		if (response.getStatusCodeValue() == HttpStatus.SC_OK) {
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);

			CognitoAuthResponse authModel = mapper.treeToValue(mapper.readTree(response.getBody()),
					CognitoAuthResponse.class);
			req.setAttribute("tokenEmail", authModel.getEmail());
			logger.info("email: " + req.getAttribute("tokenEmail"));
			req.setAttribute("token", cognitoToken);

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
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String tokenEmail = (String) req.getAttribute("tokenEmail");
		if(stage.equals("dev") && tokenEmail == null) {
			logger.info("\n Token bypassed by Dev Route");
		}
		
		return tokenEmail;
	}

	/**
	 * Returns email associated with token. // * @return String
	 */
	public String extractToken() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String token = (String) req.getAttribute("token");
		return token;
	}

}
