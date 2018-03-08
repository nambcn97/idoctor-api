package com.fpt.idoctor.security;

public class AuthenticationResponse {

    /** Status */
    private int code;

    /** Message */
    private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    

}
