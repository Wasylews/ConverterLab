package com.genius.wasylews.domain.model;

public class Currency {
    private String mAsk;
    private String mBid;
    private String mCode;
    private String mName;

    public String getAsk() {
        return mAsk;
    }

    public void setAsk(String ask) {
        mAsk = ask;
    }

    public String getBid() {
        return mBid;
    }

    public void setBid(String bid) {
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
