package com.fpt.idoctor.api.response;

import java.util.List;

import com.fpt.idoctor.bean.SymptomBean;

public class GetAllSymptomResponse extends BaseResponse {
	private List<SymptomBean> symptoms;
	public List<SymptomBean> getSymptoms() {
		return this.symptoms;
	}
	public void setSymptoms(List<SymptomBean> symptoms) {
		this.symptoms = symptoms;
	}
}
