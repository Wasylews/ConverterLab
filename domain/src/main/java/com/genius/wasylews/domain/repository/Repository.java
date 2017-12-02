package com.genius.wasylews.domain.repository;

import com.genius.wasylews.domain.model.Organization;

import io.reactivex.Flowable;

public interface Repository {
    Flowable<Organization> getOrganizations();
}
