package com.revature.models;

public class CognitoLogin {

	private String sub;
	private String aud;
	private String cognitoGroups;
	private boolean emailVerified;
	public String eventId;
	public String tokenUse;
	private int authTime;
	private String iss;
	private String cognitoUsername;
	private String exp;
	private String iat;
	private String email;
	
	public CognitoLogin() {
		super();
	}
	
	public CognitoLogin(String sub, String aud, String cognitoGroups, boolean emailVerified, String eventId,
			String tokenUse, int authTime, String iss, String cognitoUsername, String exp, String iat, String email) {
		super();
		this.sub = sub;
		this.aud = aud;
		this.cognitoGroups = cognitoGroups;
		this.emailVerified = emailVerified;
		this.eventId = eventId;
		this.tokenUse = tokenUse;
		this.authTime = authTime;
		this.iss = iss;
		this.cognitoUsername = cognitoUsername;
		this.exp = exp;
		this.iat = iat;
		this.email = email;
	}
	
	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getAud() {
		return aud;
	}

	public void setAud(String aud) {
		this.aud = aud;
	}

	public String getCognitoGroups() {
		return cognitoGroups;
	}

	public void setCognitoGroups(String cognitoGroups) {
		this.cognitoGroups = cognitoGroups;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getTokenUse() {
		return tokenUse;
	}

	public void setTokenUse(String tokenUse) {
		this.tokenUse = tokenUse;
	}

	public int getAuthTime() {
		return authTime;
	}

	public void setAuthTime(int authTime) {
		this.authTime = authTime;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getCognitoUsername() {
		return cognitoUsername;
	}

	public void setCognitoUsername(String cognitoUsername) {
		this.cognitoUsername = cognitoUsername;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getIat() {
		return iat;
	}

	public void setIat(String iat) {
		this.iat = iat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
