package com.fpt.idoctor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.service.UserService;

@RestController
public class UserAPI {

	@Autowired
	UserService userService;

	/**
	 * 
	 * @param token
	 * @return GetUserInfoResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/mobile/myInfo", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> getUserInfo() throws Exception {
		return ResponseEntity.ok(userService.getUserInfo());
	}

}
