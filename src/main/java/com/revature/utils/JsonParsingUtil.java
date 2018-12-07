package com.revature.utils;

import com.google.gson.Gson;
import com.revature.models.CognitoAuthResponse;

public class JsonParsingUtil {

	public static CognitoAuthResponse parse(String json) {
		return new Gson().fromJson(json, CognitoAuthResponse.class);
	}
}
