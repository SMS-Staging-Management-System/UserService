package com.revature.utils;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.intercomm.IncognitoClient;
import com.revature.models.User;

import feign.Response;

@Component
public class IncognitoUtil {

	
	@Autowired
	private IncognitoClient ic;
	
	
	/**
	 * Create a Jwt and attach user is as private claim
	 * 
	 * @param User
	 * @return String
	 * @throws IOException 
	 * @throws SQLException
	 */
	public Boolean registerUser(String email) throws IOException {;
		Response res = ic.registerUser(email);
		return (res.status() == 200) ? true : false;
	}
	
	/**
	 * Verify Jwt is active
	 * 
	 * @param req
	 * @return Boolean
	 * @throws IOException 
	 * @throws SQLException
	 */
	public boolean incognitoVerify(HttpServletRequest req) throws IOException {
		//"Authorization" : "Bearer tokenValue"1
		String token = req.getHeader("Authorization");
		if(token != null) {
			Response res = ic.verifyIncognito(token);
			return (res.status() == 200) ? true : false;
		}
		return false;

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
