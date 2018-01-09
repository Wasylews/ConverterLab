package com.genius.wasylews.domain.repository;

import com.genius.wasylews.domain.model.Organization;

import java.util.List;

import io.reactivex.Single;

public interface Repository {

    Single<List<Organization>> getOrganizations();

    Single<Organization> getOrganization(String id);
}
