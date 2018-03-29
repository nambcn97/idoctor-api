package com.fpt.idoctor.api.request;

public class UpdateUserStatusRequest {
	private Boolean online;
	private Boolean offline;
	private Double lat;
	private Double lng;
	public Boolean getOnline() {
		return this.online;
	}
	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Boolean getOffline() {
		return this.offline;
	}
	public void setOffline(Boolean offline) {
		this.offline = offline;
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

}
