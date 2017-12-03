package com.genius.wasylews.domain.repository;

import com.genius.wasylews.domain.model.Organization;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface Repository {

    Flowable<Organization> getOrganizations();

    Single<Organization> getOrganization(int id);
}
