package com.genius.wasylews.domain.usecase;

import com.genius.wasylews.domain.model.Organization;
import com.genius.wasylews.domain.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GetOrganizationList {

    private final Repository mRepository;

    @Inject
    public GetOrganizationList(Repository repository) {
        mRepository = repository;
    }

    public List<Organization> execute() {
        return mRepository.getOrganizations()
                .collect(ArrayList<Organization>::new, ArrayList::add)
                .blockingGet();
    }
}
