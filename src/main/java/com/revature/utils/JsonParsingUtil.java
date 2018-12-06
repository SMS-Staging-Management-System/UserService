package com.revature.utils;

import com.google.gson.Gson;
import com.revature.models.CognitoLogin;

public class JsonParsingUtil {

	public static CognitoLogin parse(String json) {
		return new Gson().fromJson(json, CognitoLogin.class);
	}
}
