package com.revature.utils;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CognitoUtil {

	
	@Autowired
	private CognitoRestTemplate cognitoRestTemplate;
	
	
	/**
	 * Create a Jwt and attach user is as private claim
	 * 
	 * @param User
	 * @return String
	 * @throws IOException 
	 * @throws SQLException
	 */
	public 	ResponseEntity<String> registerUser(String email) throws IOException {
	     ResponseEntity<String> cr= cognitoRestTemplate.registerUser(email);
	     ObjectMapper mapper = new ObjectMapper();
	     JsonNode obj = mapper.readTree(cr.getBody());
	     System.out.println(obj);
	     return cr;
	}
	
	/**
	 * Verify Jwt is active
	 * 
	 * @param req
	 * @return Boolean
	 * @throws IOException 
	 * @throws SQLException
	 */
	public boolean cognitoLogin(HttpServletRequest req) throws IOException {
		//"Authorization" : "Bearer tokenValue"1
		String token = req.getHeader("Authorization");
		return cognitoRestTemplate.checkAuth(token);
	}
	
	/**
	 * Verify request of user id is from self
	 * 
	 * @param req
	 * @return Boolean
	 * @throws IOException 
	 * @throws SQLException
	 */
//	public Boolean isRequestFromSelf(HttpServletRequest req, int pId) throws IOException {
//		if(!jwtVerify(req))
//			return false;
//		if(extractUserId(req) == pId)
//			return true;
//		else
//			return false;
//	}
//	
//	/**
//	 * Return user id from jwt
//	 * 
//	 * @param req
//	 * @return Boolean
//	 * @throws IOException 
//	 * @throws SQLException
//	 */
//	public int extractUserId(HttpServletRequest req) {
//		if(req.getHeader("Authorization") != null) {
//			String[] tToken = req.getHeader("Authorization").split(" ");
//			if(tToken.length == 2) {
//				String tJwt = tToken[1];
//				try {
//					DecodedJWT jwt = JWT.decode(tJwt);
//				    Claim claim = jwt.getClaim("user_id");
//				    return claim.asInt();
//				} catch (JWTVerificationException exception){
//					return 0;
//				}
//			}
//		}
//		return 0;
//	}
//	
//	/**
//	 * Return user role from jwt
//	 * 
//	 * @param req
//	 * @return Boolean
//	 * @throws IOException 
//	 * @throws SQLException
//	 */
//	public int extractUserRoleId(HttpServletRequest req) {
//		if(req.getHeader("Authorization") != null) {
//			String[] tToken = req.getHeader("Authorization").split(" ");
//			if(tToken.length == 2) {
//				String tJwt = tToken[1];
//				try {
//					DecodedJWT jwt = JWT.decode(tJwt);
//				    Claim claim = jwt.getClaim("user_role_id");
//				    return claim.asInt();
//				} catch (JWTVerificationException exception){
//					return 0;
//				}
//			}
//		}
//		return 0;
//	}
//	
//	/**
//	 * Verify request is from Admin {2}
//	 * 
//	 * @param req
//	 * @return Boolean
//	 * @throws IOException 
//	 * @throws SQLException
//	 */
//	public Boolean isRequestFromAdmin(HttpServletRequest req) throws IOException {
//		if(!jwtVerify(req))
//			return false;
//		if(extractUserRoleId(req) == 2)
//			return true;
//		else
//			return false;
//	}

}
