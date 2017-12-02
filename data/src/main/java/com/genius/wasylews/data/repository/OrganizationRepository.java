package com.genius.wasylews.data.repository;

import com.genius.wasylews.data.net.RestAdapter;
import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.repository.Repository;

import javax.inject.Inject;

import io.reactivex.Flowable;


public class OrganizationRepository implements Repository {

    private final RestAdapter mRestAdapter;

    @Inject
    public OrganizationRepository(RestAdapter adapter) {
        mRestAdapter = adapter;
    }

    @Override
    public Flowable<Organization> getOrganizations() {
        return mRestAdapter.getOrganizations();
    }
}
