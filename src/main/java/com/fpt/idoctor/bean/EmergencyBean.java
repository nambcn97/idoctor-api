package com.fpt.idoctor.bean;

import java.util.Date;

public class EmergencyBean {
	private Long id;
	private UserBean fromUser;
	private UserBean toUser;
	private Date date;
	private String status;
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserBean getFromUser() {
		return this.fromUser;
	}
	public void setFromUser(UserBean fromUser) {
		this.fromUser = fromUser;
	}
	public UserBean getToUser() {
		return this.toUser;
	}
	public void setToUser(UserBean toUser) {
		this.toUser = toUser;
	}
	public Date getDate() {
		return this.date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
