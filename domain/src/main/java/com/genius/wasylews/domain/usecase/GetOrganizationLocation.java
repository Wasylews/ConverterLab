package com.genius.wasylews.domain.usecase;


import com.genius.wasylews.domain.location.LocationManager;
import com.genius.wasylews.domain.model.Location;
import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.repository.Repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class GetOrganizationLocation {

    private LocationManager mLocationManager;
    private Repository mRepository;

    @Inject
    public GetOrganizationLocation(LocationManager manager, Repository repository) {
        mLocationManager = manager;
        mRepository = repository;
    }

    public Single<Location> execute(String organizationId) {
        return mRepository.getOrganization(organizationId)
                .flatMap(this::getLocation);
    }

    private Single<Location> getLocation(Organization organization) {
        String locationName;

        // google maps can't find place if region == city
        if (!organization.getRegion().equals(organization.getCity())) {
            locationName = String.format("%s %s %s", organization.getRegion(),
                    organization.getCity(), organization.getAddress());
        } else {
            locationName = String.format("%s %s",
                    organization.getCity(), organization.getAddress());
        }

        return mLocationManager.findLocation(locationName)
                .firstElement()
                .toSingle();
    }
}
