package com.genius.wasylews.converterlab.di.module;

import com.genius.wasylews.converterlab.BuildConfig;
import com.genius.wasylews.converterlab.di.scope.PerActivity;
import com.genius.wasylews.converterlab.view.activity.HomeActivity;
import com.genius.wasylews.data.net.RestService;
import com.genius.wasylews.data.repository.OrganizationRepository;
import com.genius.wasylews.domain.repository.Repository;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class AppModule {

    @Singleton
    @Binds
    abstract Repository provideRepository(OrganizationRepository repository);

    @Singleton
    @Provides
    static RestService provideRestService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(RestService.class);
    }

    @PerActivity
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity homeActivityInjector();
}
