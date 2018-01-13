package com.genius.wasylews.converterlab.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.converterlab.presenter.MapPresenter;
import com.genius.wasylews.converterlab.view.BaseMapView;
import com.genius.wasylews.domain.model.Location;
import com.genius.wasylews.domain.model.Organization;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, BaseMapView {

    @Inject
    MapPresenter presenter;

    private static final String EXTRA_ORGANIZATION = "ORGANIZATION_ID";

    private GoogleMap mMap;

    public static Intent newIntent(Context context, Organization organization) {
        Intent mapIntent = new Intent(context, MapActivity.class);
        mapIntent.putExtra(EXTRA_ORGANIZATION, organization.getId());
        return mapIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String organizationId = savedInstanceState.getString(EXTRA_ORGANIZATION);
        presenter.showOnMap(organizationId);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void showMarker(Location location) {
        LatLng marker = new LatLng(location.getLat(), location.getLng());
        mMap.addMarker(new MarkerOptions().position(marker));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }
}
