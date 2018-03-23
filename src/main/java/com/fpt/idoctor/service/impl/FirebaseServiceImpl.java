package com.fpt.idoctor.service.impl;

import static com.fpt.idoctor.common.constant.AppConstants.FCM_API;
import static com.fpt.idoctor.common.constant.AppConstants.FCM_TOKEN;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fpt.idoctor.service.FirebaseService;
import com.fpt.idoctor.service.UserService;
@Service
public class FirebaseServiceImpl implements FirebaseService {
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<String> sendNotification(JSONObject request) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "key=" + FCM_TOKEN);
		httpHeaders.set("Content-Type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<String>(
				request.toString(), httpHeaders);
		ResponseEntity<String> response = restTemplate.postForEntity(FCM_API,
				httpEntity, String.class);
		return response;

	}

}
