package com.genius.wasylews.data.database.model.mapper;


import com.genius.wasylews.data.database.model.CityModel;
import com.genius.wasylews.data.database.model.OrganizationModel;
import com.genius.wasylews.data.database.model.RegionModel;
import com.genius.wasylews.data.net.model.OrganizationEntity;

public class OrganizationModelMapper {

    public static OrganizationModel transform(String regionName, String cityName, OrganizationEntity organizationEntity) {
        OrganizationModel model = new OrganizationModel();

        model.setId(organizationEntity.getId());
        model.setTitle(organizationEntity.getTitle());

        RegionModel region = new RegionModel();
        region.setId(organizationEntity.getRegionId());
        region.setName(regionName);
        model.setRegion(region);

        CityModel city = new CityModel();
        city.setId(organizationEntity.getCityId());
        city.setName(cityName);
        model.setCity(city);

        model.setPhone(organizationEntity.getPhone());
        model.setAddress(organizationEntity.getAddress());
        model.setLink(organizationEntity.getLink());

        return model;
    }
}
