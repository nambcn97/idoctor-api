package com.fpt.idoctor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.idoctor.api.request.AuthTokenRequest;
import com.fpt.idoctor.api.request.LoginRequest;
import com.fpt.idoctor.api.request.SignUpUserRequest;
import com.fpt.idoctor.api.response.AuthTokenResponse;
import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.api.response.LoginResponse;
import com.fpt.idoctor.service.AuthenticateService;

@RestController
@RequestMapping("/auth")
public class AuthenticateAPI {
	@Autowired
	AuthenticateService authenticateService;

//	@RequestMapping(value = "/{token}", method = RequestMethod.POST)
//	public ResponseEntity<AuthTokenResponse> authenticateToken(@RequestBody AuthTokenRequest request) throws Exception {
//		return ResponseEntity.ok(authenticateService.authenticateToken(request));
//	}
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ResponseEntity<LoginResponse> authenticateLogin(@RequestBody LoginRequest request) throws Exception {
//		return ResponseEntity.ok(authenticateService.authenticateLogin(request));
//	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> signUpUser(@RequestBody SignUpUserRequest req) throws Exception {
		return ResponseEntity.ok(authenticateService.signUpUser(req));
	}
	
	@RequestMapping("testApi")
	public String testApi() throws Exception {
		throw new Exception();
	}
	
}
