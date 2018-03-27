package com.fpt.idoctor.api.response;

import java.util.List;

import com.fpt.idoctor.bean.SpecialtyBean;

public class GetAllSpecialtyResponse extends BaseResponse {
	private List<SpecialtyBean> specialties;
	public List<SpecialtyBean> getSpecialties() {
		return this.specialties;
	}

	public void setSpecialties(List<SpecialtyBean> specialties) {
		this.specialties = specialties;
	}
}
