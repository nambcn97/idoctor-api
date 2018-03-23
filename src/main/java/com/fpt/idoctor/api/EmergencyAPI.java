package com.fpt.idoctor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.idoctor.api.request.SendEmergencyRequest;
import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.service.EmergencyService;

@RestController
public class EmergencyAPI {
	@Autowired
	private EmergencyService emergencyService;

	@RequestMapping(value = "/get/sendEmergency", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> sendEmergency(
			@RequestBody SendEmergencyRequest req) throws Exception {
		return ResponseEntity.ok(emergencyService.sendEmergencyToDoctor(req));
	}

}
