package com.example.islam.cars.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlacemarksItem implements Parcelable {

    public static final Parcelable.Creator<PlacemarksItem> CREATOR = new Parcelable.Creator<PlacemarksItem>() {
        @Override
        public PlacemarksItem createFromParcel(Parcel source) {
            return new PlacemarksItem(source);
        }

        @Override
        public PlacemarksItem[] newArray(int size) {
            return new PlacemarksItem[size];
        }
    };
    @SerializedName("address")
    private String address;
    @SerializedName("fuel")
    private int fuel;
    @SerializedName("exterior")
    private String exterior;
    @SerializedName("coordinates")
    private List<Double> coordinates;
    @SerializedName("name")
    private String name;
    @SerializedName("engineType")
    private String engineType;
    @SerializedName("vin")
    private String vin;
    @SerializedName("interior")
    private String interior;

    public PlacemarksItem() {
    }

    protected PlacemarksItem(Parcel in) {
        this.address = in.readString();
        this.fuel = in.readInt();
        this.exterior = in.readString();
        this.coordinates = new ArrayList<Double>();
        in.readList(this.coordinates, Double.class.getClassLoader());
        this.name = in.readString();
        this.engineType = in.readString();
        this.vin = in.readString();
        this.interior = in.readString();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeInt(this.fuel);
        dest.writeString(this.exterior);
        dest.writeList(this.coordinates);
        dest.writeString(this.name);
        dest.writeString(this.engineType);
        dest.writeString(this.vin);
        dest.writeString(this.interior);
    }
}