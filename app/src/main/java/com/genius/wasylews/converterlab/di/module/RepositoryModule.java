package com.genius.wasylews.converterlab.di.module;

import com.genius.wasylews.converterlab.BuildConfig;
import com.genius.wasylews.data.net.RestService;
import com.genius.wasylews.data.repository.OrganizationRepository;
import com.genius.wasylews.domain.repository.Repository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public interface RepositoryModule {

    @Singleton
    @Binds
    Repository provideRepository(OrganizationRepository repository);

    @Singleton
    @Provides
    static RestService provideRestService(Retrofit retrofit) {
        return retrofit.create(RestService.class);
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build();
    }

    @Singleton
    @Provides
    static OkHttpClient provideClient() {
        return new OkHttpClient.Builder()
                .build();
    }
}
