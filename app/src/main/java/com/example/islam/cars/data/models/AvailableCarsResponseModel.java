package com.example.islam.cars.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AvailableCarsResponseModel implements Parcelable {

    public static final Parcelable.Creator<AvailableCarsResponseModel> CREATOR = new Parcelable.Creator<AvailableCarsResponseModel>() {
        @Override
        public AvailableCarsResponseModel createFromParcel(Parcel source) {
            return new AvailableCarsResponseModel(source);
        }

        @Override
        public AvailableCarsResponseModel[] newArray(int size) {
            return new AvailableCarsResponseModel[size];
        }
    };
    @SerializedName("placemarks")
    private List<PlacemarksItem> placemarks;

    public AvailableCarsResponseModel() {
    }

    protected AvailableCarsResponseModel(Parcel in) {
        this.placemarks = new ArrayList<PlacemarksItem>();
        in.readList(this.placemarks, PlacemarksItem.class.getClassLoader());
    }

    public List<PlacemarksItem> getPlacemarks() {
        return placemarks;
    }

    public void setPlacemarks(List<PlacemarksItem> placemarks) {
        this.placemarks = placemarks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.placemarks);
    }
}