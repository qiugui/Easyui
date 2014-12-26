 package com.qg.domain;

import org.springframework.stereotype.Component;
 @Component("city")
 public class City {

	 private int cityId;
	 private String cityName;
	 
	 public City(){
		 
	 }
	 
	public City(int cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	 
}

 