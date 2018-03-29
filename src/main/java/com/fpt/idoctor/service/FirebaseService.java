package com.fpt.idoctor.service;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

public interface FirebaseService {
	public ResponseEntity<String> sendNotification(JSONObject request)
			throws Exception;
}
