package com.genius.wasylews.converterlab.di.module;

import com.genius.wasylews.converterlab.BuildConfig;
import com.genius.wasylews.data.net.RestService;
import com.genius.wasylews.data.repository.OrganizationRepository;
import com.genius.wasylews.domain.repository.Repository;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {
    @Provides
    @Singleton
    Repository provideRepository(OrganizationRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    RestService provideRestService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(RestService.class);
    }
}
