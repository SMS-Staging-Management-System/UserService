package com.revature.utils;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TokenUtil {

	/**
	 * receives and encoded token as a String, decodes it, and returns the IdToken value
	 * 
	 * @param token - a cognito token encoded using Base64
	 * @return the value of the IdToken field of the decoded token or null if
	 *         IOException was thrown
	 */
	public static String decodeToken(String token) {
		
		byte[] byteArray = Base64.decodeBase64(token.getBytes());
		String decodedToken = new String(byteArray);

		try {
			return getIdToken(decodedToken);
		} catch (IOException e) {
			// TODO log "com.revature.utils.TokenUtil.getIdToken() threw IOException";
			return null;
		} catch (ParseException e) {
			// TODO log "com.revature.utils.TokenUtil.getIdToken() threw ParseException";
			return null;
		}
	}

	/**
	 * receives a JSON string and returns the value in the IdToken field
	 * 
	 * @param decodedToken - a JSON string created as the result of decoding a token
	 * @return IdToken - a String containing the value associated with the "IdToken" field of decodedToken
	 * @throws IOException
	 * @throws ParseException
	 */
	private static String getIdToken(String decodedToken) throws IOException, ParseException {

		JSONObject jsonObject = (JSONObject) new JSONParser().parse(decodedToken);
		JSONObject AuthenticationResult = (JSONObject) jsonObject.get("AuthenticationResult");
		String IdToken = AuthenticationResult.get("IdToken").toString();
		return IdToken;
	}

}
