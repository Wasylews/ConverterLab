package com.genius.wasylews.data.database.model.mapper;

import com.genius.wasylews.data.database.model.CurrencyQueryModel;
import com.genius.wasylews.domain.model.Currency;

class CurrencyMapper {

    public static Currency transform(CurrencyQueryModel model) {
        Currency currency = new Currency();
        currency.setCode(model.getCode());
        currency.setName(model.getName());
        currency.setAsk(model.getAsk());
        currency.setBid(model.getBid());
        currency.setAskUp(model.isAskUp());
        currency.setBidUp(model.isBidUp());

        return currency;
    }
}
