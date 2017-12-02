package com.genius.wasylews.domain.usecase;

import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.repository.Repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class GetOrganizationList {

    private Repository mRepository;

    @Inject
    public GetOrganizationList(Repository repository) {
        mRepository = repository;
    }

    public Flowable<Organization> execute() {
        return mRepository.getOrganizations();
    }
}
