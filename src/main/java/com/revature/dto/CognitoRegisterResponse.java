package com.revature.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CognitoRegisterResponse {

	private String Username;

	//Does not take in attributes list field

	private String UserCreateData;

	private String UserLastModifiedData;
	
	public Boolean Enabled;
	
	public String UserStatus;

	public CognitoRegisterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CognitoRegisterResponse(String username, String userCreateData, String userLastModifiedData, Boolean enabled,
			String userStatus) {
		super();
		Username = username;
		UserCreateData = userCreateData;
		UserLastModifiedData = userLastModifiedData;
		Enabled = enabled;
		UserStatus = userStatus;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getUserCreateData() {
		return UserCreateData;
	}

	public void setUserCreateData(String userCreateData) {
		UserCreateData = userCreateData;
	}

	public String getUserLastModifiedData() {
		return UserLastModifiedData;
	}
	public void setUserLastModifiedData(String userLastModifiedData) {
		UserLastModifiedData = userLastModifiedData;
	}

	public Boolean getEnabled() {
		return Enabled;
	}

	public void setEnabled(Boolean enabled) {
		Enabled = enabled;
	}

	public String getUserStatus() {
		return UserStatus;
	}

	public void setUserStatus(String userStatus) {
		UserStatus = userStatus;
	}

	

}
