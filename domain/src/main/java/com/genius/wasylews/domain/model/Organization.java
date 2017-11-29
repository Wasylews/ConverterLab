package com.genius.wasylews.domain.model;

import java.util.Map;

public class Organization {

    private Integer mId;
    private String mTitle;
    private String mRegionId;
    private String mCityId;
    private String mPhone;
    private String mAddress;
    private Map<String, Currency> mCurrencies;

    public Organization(Integer id) {
        mId = id;
    }

    public Integer getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getRegionId() {
        return mRegionId;
    }

    public void setRegionId(String regionId) {
        mRegionId = regionId;
    }

    public String getCityId() {
        return mCityId;
    }

    public void setCityId(String cityId) {
        mCityId = cityId;
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

    public Map<String, Currency> getCurrencies() {
        return mCurrencies;
    }

    public void setCurrencies(Map<String, Currency> currencies) {
        mCurrencies = currencies;
    }
}
