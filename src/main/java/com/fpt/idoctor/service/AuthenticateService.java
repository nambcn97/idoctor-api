package com.fpt.idoctor.service;

import com.fpt.idoctor.api.request.SignUpUserRequest;
import com.fpt.idoctor.api.response.BaseResponse;

public interface AuthenticateService {
	public BaseResponse logout(String header);

	public BaseResponse signUpUser(SignUpUserRequest req) throws Exception;

}
