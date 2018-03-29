package com.fpt.idoctor.api.request;

public class FindDoctorRequest {
	private Double lat;
	private Double lng;
	private Double radius;
	private String others;
	private Long symptomId;

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
	public String getOthers() {
		return this.others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public Long getSymptomId() {
		return this.symptomId;
	}
	public void setSymptomId(Long symptomId) {
		this.symptomId = symptomId;
	}

}
