package com.fpt.idoctor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.idoctor.api.request.GetSpecialtyRequest;
import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.service.SpecialtyService;

@RestController
public class SpecialtyAPI {
	@Autowired
	private SpecialtyService specialtyService;

	@RequestMapping(value = "/mobile/specialty", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> getSpecialty(
			@RequestBody GetSpecialtyRequest req) {
		return ResponseEntity.ok(specialtyService.getSpecialty(req.getId()));
	}
}
