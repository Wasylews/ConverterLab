package com.genius.wasylews.data.net.model;


import com.google.gson.annotations.SerializedName;

public class CurrencyEntity {

    @SerializedName("ask")
    private Double mAsk;

    @SerializedName("bid")
    private Double mBid;

    public Double getAsk() {
        return mAsk;
    }

    public void setAsk(Double ask) {
        mAsk = ask;
    }

    public Double getBid() {
        return mBid;
    }

    public void setBid(Double bid) {
        mBid = bid;
    }
}
