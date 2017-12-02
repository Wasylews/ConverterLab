package com.genius.wasylews.converterlab.di.module;

import com.genius.wasylews.data.net.OrganizationRestAdapter;
import com.genius.wasylews.data.net.RestAdapter;
import com.genius.wasylews.data.repository.OrganizationRepository;
import com.genius.wasylews.domain.repository.Repository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface AppModule {
    @Binds
    @Singleton
    Repository provideRepository(OrganizationRepository repository);

    @Binds
    @Singleton
    RestAdapter provideRestAdapter(OrganizationRestAdapter adapter);
}
