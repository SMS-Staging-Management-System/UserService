package com.revature.utils;

import com.google.gson.Gson;
import com.revature.models.CognitoResponse;

public class JsonParsingUtil {

	public static CognitoResponse parse(String json) {
		return new Gson().fromJson(json, CognitoResponse.class);
	}
}
