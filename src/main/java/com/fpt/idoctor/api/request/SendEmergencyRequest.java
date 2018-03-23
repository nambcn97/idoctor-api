package com.fpt.idoctor.api.request;

public class SendEmergencyRequest {
	private Double lat;
	private Double lng;
	private Double radius;
	private Boolean loggedIn;
	private String phone;
	private String deviceId;
	public Boolean getLoggedIn() {
		return this.loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public Double getRadius() {
		return this.radius;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
	}
	public String getDeviceId() {
		return this.deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
