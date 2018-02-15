package com.genius.wasylews.device.location;


import com.genius.wasylews.domain.location.LocationManager;
import com.genius.wasylews.domain.model.Location;
import com.genius.wasylews.domain.network.NetworkManager;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class LocationProvider implements LocationManager {

    private GeoDataClient mGeoDataClient;
    private NetworkManager mNetworkManager;

    @Inject
    public LocationProvider(GeoDataClient geoDataClient, NetworkManager networkManager) {
        mGeoDataClient = geoDataClient;
        mNetworkManager = networkManager;
    }

    @Override
    public Flowable<Location> findLocation(String name) {
        if (!mNetworkManager.isConnected()) {
            return Flowable.error(new Exception("Check your internet connection"));
        }

        return getPlaceId(name)
                .flatMap(this::getPlaceById)
                .map(place -> {
                    LatLng location = place.getLatLng();
                    return new Location(location.latitude, location.longitude);
                });
    }

    private Flowable<String> getPlaceId(String name) {
        AutocompleteFilter filter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_GEOCODE)
                .build();

        Task<AutocompletePredictionBufferResponse> task = mGeoDataClient.getAutocompletePredictions(
                name,
                null,
                filter);

        return GmsTaskMapper.asSingle(task)
                .doAfterSuccess(zzb::release)
                .toFlowable()
                .flatMapIterable(autocompletePredictions -> autocompletePredictions)
                .map(AutocompletePrediction::getPlaceId);
    }

    private Flowable<Place> getPlaceById(String placeId) {
        Task<PlaceBufferResponse> placeTask = mGeoDataClient.getPlaceById(placeId);

        return GmsTaskMapper.asSingle(placeTask)
                .doAfterSuccess(zzb::release)
                .toFlowable()
                .flatMapIterable(places -> places);
    }
}
