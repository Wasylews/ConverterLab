package com.genius.wasylews.converterlab.presenter;


import com.genius.wasylews.converterlab.di.scope.PerActivity;
import com.genius.wasylews.converterlab.view.BaseMapView;
import com.genius.wasylews.domain.usecase.GetOrganizationLocation;

@PerActivity
public class MapPresenter {

    private GetOrganizationLocation mGetOrganizationLocation;
    private BaseMapView mView;

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
                .subscribe(mView::showMarker);
    }
}
