package com.genius.wasylews.data.net;

import com.genius.wasylews.domain.model.Organization;

import io.reactivex.Flowable;

public interface RestAdapter {
    Flowable<Organization> getOrganizations();
}
