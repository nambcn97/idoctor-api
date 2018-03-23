package com.fpt.idoctor.api.response;

import java.util.List;

import com.fpt.idoctor.bean.EmergencyBean;

public class SendEmergencyResponse extends BaseResponse {
	private List<EmergencyBean> emergencies;
	public List<EmergencyBean> getEmergencies() {
		return this.emergencies;
	}

	public void setEmergencies(List<EmergencyBean> emergencies) {
		this.emergencies = emergencies;
	}
}
