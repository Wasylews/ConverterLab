package com.genius.wasylews.converterlab.di.module;

import com.genius.wasylews.converterlab.BuildConfig;
import com.genius.wasylews.converterlab.di.scope.PerActivity;
import com.genius.wasylews.converterlab.view.activity.HomeActivity;
import com.genius.wasylews.data.net.RestService;
import com.genius.wasylews.data.repository.OrganizationRepository;
import com.genius.wasylews.domain.repository.Repository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class AppModule {

    @Singleton
    @Binds
    abstract Repository provideRepository(OrganizationRepository repository);

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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Singleton
    @Provides
    static OkHttpClient provideClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @PerActivity
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity homeActivityInjector();
}
