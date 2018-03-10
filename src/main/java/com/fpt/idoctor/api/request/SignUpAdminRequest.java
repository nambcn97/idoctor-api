package com.fpt.idoctor.api.request;

public class SignUpAdminRequest {
	private String username;
	private String fullName;
	private String phone;
	private String password;
	// private String photo;
	private String address;
	private Boolean gender;
	private Long roleId;
	private Long specialtyID;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getSpecialtyID() {
		return this.specialtyID;
	}

	public void setSpecialtyIPD(Long specialtyID) {
		this.specialtyID = specialtyID;
	}

}
