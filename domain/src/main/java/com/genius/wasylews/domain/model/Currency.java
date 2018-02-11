package com.genius.wasylews.domain.model;

public class Currency {
    private Double mAsk;
    private Double mBid;
    private String mCode;
    private String mName;
    private boolean mAskUp;
    private boolean mBidUp;

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

    public boolean isAskUp() {
        return mAskUp;
    }

    public void setAskUp(boolean askUp) {
        mAskUp = askUp;
    }

    public boolean isBidUp() {
        return mBidUp;
    }

    public void setBidUp(boolean bidUp) {
        mBidUp = bidUp;
    }
}
