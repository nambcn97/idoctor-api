package com.fpt.idoctor.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.idoctor.api.request.SignUpUserRequest;
import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.service.AuthenticateService;

@RestController
@RequestMapping("/auth")
public class AuthenticateAPI {
	@Autowired
	AuthenticateService authenticateService;

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> logout(HttpServletRequest request) {
		return ResponseEntity.ok(
				authenticateService.logout(request.getHeader("Authorization")));
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> signUpUser(
			@RequestBody SignUpUserRequest req) throws Exception {
		return ResponseEntity.ok(authenticateService.signUpUser(req));
	}

	@RequestMapping("testApi")
	public String testApi() throws Exception {
		throw new Exception();
	}

}
