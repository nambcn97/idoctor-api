package com.fpt.idoctor.api.response;

import com.fpt.idoctor.bean.SpecialtyBean;

public class GetSpecialtyResponse extends BaseResponse {
	private SpecialtyBean specialty;
	public SpecialtyBean getSpecialty() {
		return this.specialty;
	}

	public void setSpecialty(SpecialtyBean specialty) {
		this.specialty = specialty;
	}
}
