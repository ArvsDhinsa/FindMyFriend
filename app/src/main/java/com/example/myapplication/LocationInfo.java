package com.example.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class LocationInfo {
    public Double latitude = 0.0;
    public Double longitude = 0.0;

    public LocationInfo(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
