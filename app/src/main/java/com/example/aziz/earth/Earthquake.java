package com.example.aziz.earth;

public class Earthquake {

    private String mMagnitude;
    private String mLocation;
    private String mUrl;
    private Long mTimeInMilliSeconds;

    public Earthquake(String magnitude, String place, String url, long timeInMilliSeconds) {
        this.mMagnitude = magnitude;
        this.mLocation = place;
        this.mUrl = url;
        this.mTimeInMilliSeconds = timeInMilliSeconds;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliSeconds() {
        return mTimeInMilliSeconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
