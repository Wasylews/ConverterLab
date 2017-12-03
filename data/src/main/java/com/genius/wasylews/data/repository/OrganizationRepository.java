package com.genius.wasylews.data.repository;

import com.genius.wasylews.data.net.RestService;
import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;


public class OrganizationRepository implements Repository {

    private final RestService mRestAdapter;

    @Inject
    public OrganizationRepository(RestService adapter) {
        mRestAdapter = adapter;
    }

    @Override
    public Flowable<Organization> getOrganizations() {
        return mRestAdapter.getOrganizations();
    }

    @Override
    public Single<Organization> getOrganization(int id) {
        return null;
    }
}
