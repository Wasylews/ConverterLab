package com.genius.wasylews.data.net.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ServiceResponse {

    @SerializedName("date")
    private String mDate;

    @SerializedName("organizations")
    private List<OrganizationEntity> mOrganizationList;

    @SerializedName("currencies")
    private Map<String, String> mCurrencies;

    @SerializedName("regions")
    private Map<String, String> mRegions;

    @SerializedName("cities")
    private Map<String, String> mCities;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public List<OrganizationEntity> getOrganizationList() {
        return mOrganizationList;
    }

    public void setOrganizationList(List<OrganizationEntity> organizationList) {
        mOrganizationList = organizationList;
    }

    public Map<String, String> getCurrencies() {
        return mCurrencies;
    }

    public void setCurrencies(Map<String, String> currencies) {
        mCurrencies = currencies;
    }

    public Map<String, String> getRegions() {
        return mRegions;
    }

    public void setRegions(Map<String, String> regions) {
        mRegions = regions;
    }

    public Map<String, String> getCities() {
        return mCities;
    }

    public void setCities(Map<String, String> cities) {
        mCities = cities;
    }
}
