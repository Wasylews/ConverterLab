package com.genius.wasylews.device.location;


import com.genius.wasylews.domain.location.LocationManager;
import com.genius.wasylews.domain.model.Location;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class LocationProvider implements LocationManager {

    private GeoDataClient mGeoDataClient;

    @Inject
    public LocationProvider(GeoDataClient geoDataClient) {
        mGeoDataClient = geoDataClient;
    }

    public Flowable<Location> findLocation(String name) {
        return getPlaceId(name)
                .map(placeId -> mGeoDataClient.getPlaceById(placeId).getResult())
                .doAfterNext(zzb::release)
                .flatMapIterable(places -> places)
                .map(place -> {
                    LatLng location = place.getLatLng();
                    return new Location(location.latitude, location.longitude);
                });
    }

    private Flowable<String> getPlaceId(String name) {
        AutocompleteFilter filter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_GEOCODE)
                .build();

        AutocompletePredictionBufferResponse response = mGeoDataClient.getAutocompletePredictions(
                name,
                null,
                filter).getResult();

        return Flowable.fromIterable(response)
                .map(AutocompletePrediction::getPlaceId)
                .doOnComplete(response::release);
    }
}
