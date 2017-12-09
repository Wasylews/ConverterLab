package com.genius.wasylews.converterlab.presenter;


import com.genius.wasylews.converterlab.di.scope.PerActivity;
import com.genius.wasylews.converterlab.view.BaseHomeView;
import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.usecase.GetOrganizationList;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class HomePresenter {

    private final GetOrganizationList mGetOrganizationList;
    private BaseHomeView mView;

    @Inject
    public HomePresenter(GetOrganizationList getOrganizationList) {
        mGetOrganizationList = getOrganizationList;
    }

    public BaseHomeView getView() {
        return mView;
    }

    public void setView(BaseHomeView view) {
        mView = view;
    }

    public void loadList() {
        List<Organization> list = mGetOrganizationList.execute();

        mView.showOrganizations(list);
    }
}
