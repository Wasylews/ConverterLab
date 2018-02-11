package com.genius.wasylews.converterlab.presenter;

import com.genius.wasylews.converterlab.di.scope.PerActivity;
import com.genius.wasylews.converterlab.view.BaseDetailsView;
import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.usecase.GetOrganization;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@PerActivity
public class DetailsPresenter {

    private final GetOrganization mGetOrganization;
    private BaseDetailsView mView;

    @Inject
    public DetailsPresenter(GetOrganization getOrganization) {
        mGetOrganization = getOrganization;
    }

    public void setView(BaseDetailsView view) {
        mView = view;
    }

    public void loadOrganization(String id) {
        mGetOrganization.execute(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::showOrganization);
    }

    public void openOrganizationSite(Organization organization) {
        mView.openLink(organization.getLink());
    }

    public void showOrganizationOnMap(Organization organization) {
        mView.openMap(organization);

    }

    public void callOrganization(Organization organization) {
        mView.makeCall(organization.getPhone());
    }
}
