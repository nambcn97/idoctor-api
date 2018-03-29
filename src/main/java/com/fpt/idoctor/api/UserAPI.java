package com.fpt.idoctor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.idoctor.api.request.EditMyInfoRequest;
import com.fpt.idoctor.api.request.FindDoctorRequest;
import com.fpt.idoctor.api.request.UpdateDataRequest;
import com.fpt.idoctor.api.request.UpdateUserStatusRequest;
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

	@RequestMapping(value = "/mobile/updateData", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> updateData(
			@RequestBody UpdateDataRequest req) throws Exception {
		return ResponseEntity.ok(userService.updateData(req));
	}
	@RequestMapping(value = "/mobile/doctor", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> findDoctor(
			@RequestBody FindDoctorRequest req) throws Exception {
		return ResponseEntity.ok(userService.findDoctor(req));
	}

	@RequestMapping(value = "/mobile/updateStatus", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> updateUserStatus(
			UpdateUserStatusRequest req) {
		return ResponseEntity.ok(userService.updateUserStatus(req));
	}

	@RequestMapping(value = "/mobile/myInfo", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> getMyInfo() {
		return ResponseEntity.ok(userService.getMyInfo());
	}

	@RequestMapping(value = "/mobile/editMyInfo", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> editMyInfo(
			@RequestBody EditMyInfoRequest req) throws Exception {
		return ResponseEntity.ok(userService.editMyInfo(req));
	}
}
