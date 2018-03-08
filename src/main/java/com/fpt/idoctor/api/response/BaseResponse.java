package com.fpt.idoctor.api.response;

import org.springframework.context.MessageSource;

import com.fpt.idoctor.common.constant.ApiConstants;

public class BaseResponse {
	private Integer resultCode;
	private String resultMsg;

	public BaseResponse() {

	}

	public BaseResponse(Integer resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public void buildSuccessful() {
		this.resultCode = ApiConstants.SUCCESS_CODE;
		this.resultMsg = ApiConstants.SUCCESS_MSG;
	}

	public void buildFailed() {
		this.resultCode = ApiConstants.FAILED_CODE;
		this.resultMsg = ApiConstants.FAILED_MSG;
	}

	public void buildFailed(Integer resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public void i18nProcess(MessageSource i18n) {

	}
}
