package com.fpt.idoctor.service;

import com.fpt.idoctor.api.request.SendEmergencyRequest;
import com.fpt.idoctor.api.response.BaseResponse;

public interface EmergencyService {
	public BaseResponse sendEmergencyToDoctor(SendEmergencyRequest req);
}
