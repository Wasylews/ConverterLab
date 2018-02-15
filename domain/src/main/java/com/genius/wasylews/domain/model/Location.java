package com.genius.wasylews.domain.model;

public class Location {
    private double mLat = -1;
    private double mLng = -1;

    public Location() {}

    public Location(double lat, double lng) {
        mLat = lat;
        mLng = lng;
    }

    public double getLat() {
        return mLat;
    }

    public double getLng() {
        return mLng;
    }

    public boolean isValid() {
        return mLat != -1 && mLng != -1;
    }
}
