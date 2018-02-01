package com.genius.wasylews.data.database.model;

import com.genius.wasylews.data.database.Database;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.QueryModel;

@QueryModel(database = Database.class)
public class CurrencyQueryModel {

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private Double ask;

    @Column
    private Double bid;

    @Column(getterName = "isAskUp")
    private boolean askUp;

    @Column(getterName = "isBidUp")
    private boolean bidUp;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public boolean isAskUp() {
        return askUp;
    }

    public void setAskUp(boolean askUp) {
        this.askUp = askUp;
    }

    public boolean isBidUp() {
        return bidUp;
    }

    public void setBidUp(boolean bidUp) {
        this.bidUp = bidUp;
    }
}
