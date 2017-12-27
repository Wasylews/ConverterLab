package com.genius.wasylews.data.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class OrganizationEntity {

    @SerializedName("id")
    private String mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("regionId")
    private String mRegionId;

    @SerializedName("cityId")
    private String mCityId;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("address")
    private String mAddress;

    @SerializedName("link")
    private String mLink;

    @SerializedName("currencies")
    private Map<String, CurrencyEntity> mCurrencies;

    public OrganizationEntity(String id) {
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

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public Map<String, CurrencyEntity> getCurrencies() {
        return mCurrencies;
    }

    public void setCurrencies(Map<String, CurrencyEntity> currencies) {
        mCurrencies = currencies;
    }
}
