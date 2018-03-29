package com.fpt.idoctor.api.request;

public class EditMyInfoRequest {
	private String fullName;
	private String phone;
	private String address;
	private Double lat;
	private Double lng;
	private Boolean gender;
	private String workAddress;
	private Long specialtyId;
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
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLat() {
		return this.lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return this.lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Boolean getGender() {
		return this.gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getWorkAddress() {
		return this.workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public Long getSpecialtyId() {
		return this.specialtyId;
	}
	public void setSpecialtyId(Long specialtyId) {
		this.specialtyId = specialtyId;
	}

}
