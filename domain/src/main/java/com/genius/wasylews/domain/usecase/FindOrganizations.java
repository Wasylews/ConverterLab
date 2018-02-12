package com.genius.wasylews.domain.usecase;

import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.repository.Repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;


@Singleton
public class FindOrganizations {

    private final Repository mRepository;

    @Inject
    public FindOrganizations(Repository repository) {
        mRepository = repository;
    }

    public Single<List<Organization>> execute(String filter) {
        return mRepository.getOrganizations()
                .toFlowable()
                .flatMapIterable(organizations -> organizations)
                .filter(organization -> filter.isEmpty() || organization.getTitle().contains(filter))
                .toList();
    }
}
