package com.fpt.idoctor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.service.SymptomService;
@RestController
public class SymptomAPI {
	@Autowired
	SymptomService symptomService;
	@RequestMapping(value = "/get/allSymptom", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> getAllSymptom() {
		return ResponseEntity.ok(symptomService.getAllSymptom());
	}
}
