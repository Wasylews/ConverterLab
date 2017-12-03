package com.genius.wasylews.data.net;

import com.genius.wasylews.domain.model.Organization;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface RestService {

    @GET()
    Flowable<Organization> getOrganizations();
}
