package com.genius.wasylews.data.database.model.mapper;


import com.genius.wasylews.data.database.model.CurrencyModel;
import com.genius.wasylews.data.database.model.OrganizationCurrencyModel;
import com.genius.wasylews.data.database.model.OrganizationModel;
import com.genius.wasylews.data.net.model.CurrencyEntity;
import com.genius.wasylews.data.net.model.OrganizationEntity;
import com.genius.wasylews.data.net.model.ServiceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrganizationCurrencyModelMapper {

    public static List<OrganizationCurrencyModel> transform(ServiceResponse netModel) {
        List<OrganizationCurrencyModel> models = new ArrayList<>();

        Map<String, String> currencyNames = netModel.getCurrencies();
        Map<String, String> regionNames = netModel.getRegions();
        Map<String, String> cityNames = netModel.getCities();

        for (OrganizationEntity organizationEntity: netModel.getOrganizationList()) {
            String regionName = regionNames.get(organizationEntity.getRegionId());
            String cityName = cityNames.get(organizationEntity.getCityId());
            OrganizationModel organizationModel = OrganizationModelMapper.transform(regionName, cityName, organizationEntity);

            for (Map.Entry<String, CurrencyEntity> entrySet: organizationEntity.getCurrencies().entrySet()) {
                String code = entrySet.getKey();
                String name = currencyNames.get(code);
                CurrencyEntity currencyEntity = entrySet.getValue();

                CurrencyModel currencyModel = new CurrencyModel();
                currencyModel.setName(name);
                currencyModel.setCode(code);

                OrganizationCurrencyModel organizationCurrencyModel = new OrganizationCurrencyModel();
                organizationCurrencyModel.setOrganization(organizationModel);
                organizationCurrencyModel.setCurrency(currencyModel);
                organizationCurrencyModel.setAsk(currencyEntity.getAsk());
                organizationCurrencyModel.setBid(currencyEntity.getBid());

                models.add(organizationCurrencyModel);
            }
        }

        return models;
    }
}
