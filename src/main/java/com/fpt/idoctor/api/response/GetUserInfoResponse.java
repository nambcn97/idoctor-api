package com.fpt.idoctor.api.response;

import com.fpt.idoctor.bean.UserBean;

public class GetUserInfoResponse extends BaseResponse {
	private UserBean myInfo;

	public UserBean getMyInfo() {
		return myInfo;
	}

	public void setMyInfo(UserBean myInfo) {
		this.myInfo = myInfo;
	}

}
