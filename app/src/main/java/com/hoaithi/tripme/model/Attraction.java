package com.hoaithi.tripme.model;

import android.location.Location;

public class Attraction {
    public String mName;
    public String mPhotoref;
    public String mAddress;
    public Location mLocation;
    public String mRating;

    public Attraction(String name, String photo, String address, String lat, String lng, String rating)
    {
        this.mAddress = address;
        this.mName = name;
        this.mPhotoref = photo;
        this.mLocation = new Location("");
        this.mLocation.setLatitude(Float.parseFloat(lat));
        this.mLocation.setLongitude(Float.parseFloat(lng));
        this.mRating = rating;
    }
}
