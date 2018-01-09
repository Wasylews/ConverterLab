package com.genius.wasylews.data.database.model.mapper;


import com.genius.wasylews.data.database.model.CurrencyQueryModel;
import com.genius.wasylews.data.database.model.OrganizationModel;
import com.genius.wasylews.domain.model.Currency;
import com.genius.wasylews.domain.model.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationMapper {

    public static Organization transform(OrganizationModel model) {
        Organization organization = new Organization(model.getId());

        organization.setTitle(model.getTitle());
        organization.setAddress(model.getAddress());
        organization.setCity(model.getCity().getName());
        organization.setRegion(model.getRegion().getName());
        organization.setLink(model.getLink());
        organization.setPhone(model.getPhone());

        List<Currency> currencyList = new ArrayList<>();
        for (CurrencyQueryModel currencyModel : model.getCurrencies()) {
            Currency currency = CurrencyMapper.transform(currencyModel);
            currencyList.add(currency);
        }

        organization.setCurrencies(currencyList);

        return organization;
    }
}
