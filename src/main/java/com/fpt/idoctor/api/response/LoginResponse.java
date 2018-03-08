package com.fpt.idoctor.api.response;

import java.io.Serializable;

public class LoginResponse extends BaseResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private String token;
	private String userName;
	private String role;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
