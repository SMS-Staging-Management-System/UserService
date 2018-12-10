package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Kyle Ford
 *
 */
@JsonIgnoreProperties
public class CognitoAuthResponse {

	private String sub;
	private String aud;
	private String cognito_groups;
	private boolean email_verified;
	public String event_id;
	public String token_use;
	private int auth_time;
	private String iss;
	private String cognito_username;
	private String exp;
	private String iat;
	private String email;
	public CognitoAuthResponse() {
		super();
	}
	public CognitoAuthResponse(String sub, String aud, String cognito_groups, boolean email_verified, String event_id,
			String token_use, int auth_time, String iss, String cognito_username, String exp, String iat,
			String email) {
		super();
		this.sub = sub;
		this.aud = aud;
		this.cognito_groups = cognito_groups;
		this.email_verified = email_verified;
		this.event_id = event_id;
		this.token_use = token_use;
		this.auth_time = auth_time;
		this.iss = iss;
		this.cognito_username = cognito_username;
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
	public String getCognito_groups() {
		return cognito_groups;
	}
	public void setCognito_groups(String cognito_groups) {
		this.cognito_groups = cognito_groups;
	}
	public boolean isEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(boolean email_verified) {
		this.email_verified = email_verified;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getToken_use() {
		return token_use;
	}
	public void setToken_use(String token_use) {
		this.token_use = token_use;
	}
	public int getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(int auth_time) {
		this.auth_time = auth_time;
	}
	public String getIss() {
		return iss;
	}
	public void setIss(String iss) {
		this.iss = iss;
	}
	public String getCognito_username() {
		return cognito_username;
	}
	public void setCognito_username(String cognito_username) {
		this.cognito_username = cognito_username;
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
	@Override
	public String toString() {
		return "CognitoAuthResponse [sub=" + sub + ", aud=" + aud + ", cognito_groups=" + cognito_groups
				+ ", email_verified=" + email_verified + ", event_id=" + event_id + ", token_use=" + token_use
				+ ", auth_time=" + auth_time + ", iss=" + iss + ", cognito_username=" + cognito_username + ", exp="
				+ exp + ", iat=" + iat + ", email=" + email + "]";
	}
	
	
}