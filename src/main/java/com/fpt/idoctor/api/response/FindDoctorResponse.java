package com.fpt.idoctor.api.response;

import java.util.List;

import com.fpt.idoctor.bean.UserBean;

public class FindDoctorResponse extends BaseResponse {
	private List<UserBean> doctors;
	public List<UserBean> getDoctors() {
		return this.doctors;
	}

	public void setDoctors(List<UserBean> doctors) {
		this.doctors = doctors;
	}

}
