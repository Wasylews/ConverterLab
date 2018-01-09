package com.genius.wasylews.domain.model;

public class Location {
    private double mLat;
    private double mLng;

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
}
