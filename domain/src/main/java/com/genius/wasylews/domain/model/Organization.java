package com.genius.wasylews.domain.model;

import java.util.Map;

public class Organization {
    private String mTitle;
    private String mRegion;
    private String mCity;
    private String mPhone;
    private String mAddress;
    private String mLink;
    private Map<String, Currency> mCurrencies;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getRegion() {
        return mRegion;
    }

    public void setRegion(String region) {
        mRegion = region;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public Map<String, Currency> getCurrencies() {
        return mCurrencies;
    }

    public void setCurrencies(Map<String, Currency> currencies) {
        mCurrencies = currencies;
    }
}
