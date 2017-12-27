package com.genius.wasylews.data.net;

import com.genius.wasylews.data.net.model.ServiceResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RestService {

    @GET("currency-cash.json")
    Single<ServiceResponse> getOrganizations();
}
