package com.genius.wasylews.converterlab.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

import dagger.android.support.DaggerAppCompatActivity;

public class MapActivity extends DaggerAppCompatActivity implements OnMapReadyCallback, BaseMapView {

    @Inject
    MapPresenter mPresenter;

    private static final String EXTRA_ORGANIZATION = "ORGANIZATION_ID";

    private GoogleMap mMap;

    public static Intent newIntent(Context context, Organization organization) {
        Intent mapIntent = new Intent(context, MapActivity.class);
        mapIntent.putExtra(EXTRA_ORGANIZATION, organization.getId());
        return mapIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String organizationId = getIntent().getStringExtra(EXTRA_ORGANIZATION);
        mPresenter.setView(this);
        mPresenter.showOnMap(organizationId);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public void showMarker(Location location) {
        if (!location.isValid()) {
            Toast.makeText(this, R.string.location_not_found, Toast.LENGTH_SHORT).show();
            return;
        }

        if (mMap != null) {
            LatLng marker = new LatLng(location.getLat(), location.getLng());
            mMap.addMarker(new MarkerOptions().position(marker));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
        } else {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show();
        }
    }
}
