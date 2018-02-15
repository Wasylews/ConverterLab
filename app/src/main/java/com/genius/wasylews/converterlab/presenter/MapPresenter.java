package com.genius.wasylews.converterlab.presenter;


import com.genius.wasylews.converterlab.di.scope.PerActivity;
import com.genius.wasylews.converterlab.view.BaseMapView;
import com.genius.wasylews.domain.model.Location;
import com.genius.wasylews.domain.usecase.GetOrganizationLocation;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@PerActivity
public class MapPresenter {

    private GetOrganizationLocation mGetOrganizationLocation;
    private BaseMapView mView;

    @Inject
    public MapPresenter(GetOrganizationLocation getOrganizationLocation) {
        mGetOrganizationLocation = getOrganizationLocation;
    }

    public BaseMapView getView() {
        return mView;
    }

    public void setView(BaseMapView view) {
        mView = view;
    }

    public void showOnMap(String organizationId) {
        mGetOrganizationLocation.execute(organizationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturnItem(new Location()) // return invalid location
                .subscribe(location -> mView.showMarker(location));
    }
}
