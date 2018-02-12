package com.genius.wasylews.converterlab.presenter;


import android.util.Log;

import com.genius.wasylews.converterlab.di.scope.PerActivity;
import com.genius.wasylews.converterlab.view.BaseHomeView;
import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.usecase.FindOrganizations;
import com.genius.wasylews.domain.usecase.GetOrganizationList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@PerActivity
public class HomePresenter {

    private static final String TAG = HomePresenter.class.getSimpleName();

    private final GetOrganizationList mGetOrganizationList;
    private final FindOrganizations mFindOrganizations;
    private BaseHomeView mView;

    @Inject
    public HomePresenter(GetOrganizationList getOrganizationList, FindOrganizations findOrganizations) {
        mGetOrganizationList = getOrganizationList;
        mFindOrganizations = findOrganizations;
    }

    public BaseHomeView getView() {
        return mView;
    }

    public void setView(BaseHomeView view) {
        mView = view;
    }

    public void loadList() {
        mView.showProgress();

        mGetOrganizationList.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(mView::hideProgress)
                .subscribe(mView::showOrganizations,
                        throwable -> Log.d(TAG, "Error on subscribe", throwable));
    }

    public void openOrganizationSite(Organization organization) {
        mView.openSite(organization.getLink());
    }

    public void callOrganization(Organization organization) {
        mView.makePhoneCall(organization.getPhone());
    }

    public void showOnMap(Organization organization) {
        mView.openMap(organization);
    }

    public void showDetails(Organization organization) {
        mView.showOrganizationDetails(organization);
    }

    public void findOrganizations(String filter) {
        mFindOrganizations.execute(filter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::showOrganizations,
                        throwable -> Log.d(TAG, "Error on subscribe", throwable));
    }
}
