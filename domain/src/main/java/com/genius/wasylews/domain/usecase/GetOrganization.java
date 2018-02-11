package com.genius.wasylews.domain.usecase;


import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.repository.Repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class GetOrganization {

    private final Repository mRepository;

    @Inject
    public GetOrganization(Repository repository) {
        mRepository = repository;
    }

    public Single<Organization> execute(String id) {
        return mRepository.getOrganization(id);
    }
}
