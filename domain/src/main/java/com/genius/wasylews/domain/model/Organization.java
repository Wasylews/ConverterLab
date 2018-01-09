package com.genius.wasylews.domain.model;

import java.util.List;

public class Organization {

    private String mId;
    private String mTitle;
    private String mRegion;
    private String mCity;
    private String mPhone;
    private String mAddress;
    private String mLink;
    private List<Currency> mCurrencies;

    public Organization(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

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

    public List<Currency> getCurrencies() {
        return mCurrencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        mCurrencies = currencies;
    }
}
