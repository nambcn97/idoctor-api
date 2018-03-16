package com.fpt.idoctor.api.request;

public class FindDoctorRequest {
	private Double lat;
	private Double lng;
	private Double radius;
	private String symptoms;
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
	public String getSymptoms() {
		return this.symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

}
