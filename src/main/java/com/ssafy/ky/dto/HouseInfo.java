package com.ssafy.ky.dto;

public class HouseInfo {

	String aptCode;
	String roadName;
	String dong;
	String apartmentName;
	String jibun;
	String lng;
	String lat;
	public HouseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HouseInfo(String aptCode, String roadName, String dong, String apartmentName, String jibun, String lng, String lat) {
		super();
		this.aptCode = aptCode;
		this.roadName = roadName;
		this.dong = dong;
		this.apartmentName = apartmentName;
		this.jibun = jibun;
		this.lng = lng;
		this.lat = lat;
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	@Override
	public String toString() {
		return "HouseInfo [aptCode=" + aptCode + ", roadName=" + roadName + ", dong=" + dong + ", apartmentName="
				+ apartmentName + ", jibun=" + jibun + ", lng=" + lng + ", lat=" + lat + "]";
	}
	
}
