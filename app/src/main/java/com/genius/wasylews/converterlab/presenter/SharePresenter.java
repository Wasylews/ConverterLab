package com.genius.wasylews.converterlab.presenter;


import com.genius.wasylews.converterlab.di.scope.PerFragment;
import com.genius.wasylews.converterlab.view.fragment.BaseShareView;
import com.genius.wasylews.domain.usecase.GetOrganization;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@PerFragment
public class SharePresenter {

    private final GetOrganization mGetOrganization;
    private BaseShareView mView;

    @Inject
    public SharePresenter(GetOrganization getOrganization) {
        mGetOrganization = getOrganization;
    }

    public void setView(BaseShareView view) {
        mView = view;
    }

    public void loadOrganization(String id) {
        mGetOrganization.execute(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::showOrganization);
    }

    public void shareOrganization() {
        mView.share();
    }
}
