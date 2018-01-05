package com.genius.wasylews.domain.model;

public class Currency {
    private Double mAsk;
    private Double mBid;
    private String mCode;
    private String mName;

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

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
