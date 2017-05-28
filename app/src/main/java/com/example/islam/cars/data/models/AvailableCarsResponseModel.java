package com.example.islam.cars.data.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AvailableCarsResponseModel{

	@SerializedName("placemarks")
	private List<PlacemarksItem> placemarks;

	public void setPlacemarks(List<PlacemarksItem> placemarks){
		this.placemarks = placemarks;
	}

	public List<PlacemarksItem> getPlacemarks(){
		return placemarks;
	}
}