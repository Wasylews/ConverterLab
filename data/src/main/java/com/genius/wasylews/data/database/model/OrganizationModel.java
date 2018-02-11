package com.genius.wasylews.data.database.model;

import com.genius.wasylews.data.database.Database;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

@Table(database = Database.class, name = "organizations")
public class OrganizationModel extends BaseModel {

    @PrimaryKey
    @Column
    private String id;

    @Column
    private String title;

    @ForeignKey(saveForeignKeyModel = true)
    private CityModel city;

    @ForeignKey(saveForeignKeyModel = true)
    private RegionModel region;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private String link;

    private List<CurrencyQueryModel> currencies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }

    public RegionModel getRegion() {
        return region;
    }

    public void setRegion(RegionModel region) {
        this.region = region;
    }

    @OneToMany
    public List<CurrencyQueryModel> getCurrencies() {
        if (currencies == null || currencies.isEmpty()) {
            currencies = SQLite.select(CurrencyModel_Table.code,
                    CurrencyModel_Table.name,
                    OrganizationCurrencyModel_Table.ask,
                    OrganizationCurrencyModel_Table.bid,
                    OrganizationCurrencyModel_Table.askUp,
                    OrganizationCurrencyModel_Table.bidUp)
                    .from(OrganizationCurrencyModel.class)
                    .innerJoin(CurrencyModel.class)
                    .on(CurrencyModel_Table.code.eq(OrganizationCurrencyModel_Table.currency_code))
                    .where(OrganizationCurrencyModel_Table.organization_id.eq(id))
                    .queryCustomList(CurrencyQueryModel.class);
        }
        return currencies;
    }
}