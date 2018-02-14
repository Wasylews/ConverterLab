package com.genius.wasylews.data.database.model;

import com.genius.wasylews.data.database.Database;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = Database.class, name = "organization_currencies")
public class OrganizationCurrencyModel extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private Integer id;

    @ForeignKey(saveForeignKeyModel = true)
    private OrganizationModel organization;

    @ForeignKey(saveForeignKeyModel = true)
    private CurrencyModel currency;

    @Column
    private Double ask;

    @Column
    private Double bid;

    @Column
    private boolean askUp = true;

    @Column
    private boolean bidUp = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrganizationModel getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationModel organization) {
        this.organization = organization;
    }

    public CurrencyModel getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyModel currency) {
        this.currency = currency;
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

    @Override
    public boolean save() {
        OrganizationCurrencyModel oldModel = SQLite.select(OrganizationCurrencyModel_Table.ask,
                OrganizationCurrencyModel_Table.bid)
                .from(OrganizationCurrencyModel.class)
                .where(OrganizationCurrencyModel_Table.currency_code.eq(currency.getCode()))
                .and(OrganizationCurrencyModel_Table.organization_id.eq(organization.getId()))
                .querySingle();

        if (oldModel != null) {
            setAskUp(oldModel.getAsk() <= getAsk());
            setBidUp(oldModel.getBid() <= getBid());
        }

        return super.save();
    }
}
