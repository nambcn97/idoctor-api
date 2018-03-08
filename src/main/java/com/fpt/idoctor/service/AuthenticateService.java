package com.fpt.idoctor.service;

import com.fpt.idoctor.api.request.AuthTokenRequest;
import com.fpt.idoctor.api.request.LoginRequest;
import com.fpt.idoctor.api.request.SignUpAdminRequest;
import com.fpt.idoctor.api.request.SignUpUserRequest;
import com.fpt.idoctor.api.response.AuthTokenResponse;
import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.api.response.LoginResponse;

public interface AuthenticateService {
//	public LoginResponse authenticateLogin(LoginRequest request) throws Exception;
//
//	public AuthTokenResponse authenticateToken(AuthTokenRequest request) throws Exception;
	
	public BaseResponse signUpUser(SignUpUserRequest req) throws Exception;

}
