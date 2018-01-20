package com.genius.wasylews.converterlab.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.genius.wasylews.converterlab.App;
import com.genius.wasylews.converterlab.BuildConfig;
import com.genius.wasylews.converterlab.di.scope.PerActivity;
import com.genius.wasylews.converterlab.view.activity.DetailsActivity;
import com.genius.wasylews.converterlab.view.activity.HomeActivity;
import com.genius.wasylews.converterlab.view.activity.MapActivity;
import com.genius.wasylews.device.location.LocationProvider;
import com.genius.wasylews.domain.location.LocationManager;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = { AndroidSupportInjectionModule.class,
        RepositoryModule.class, NetworkModule.class })
public abstract class AppModule {

    @Binds
    abstract Context provideApplicationContext(App app);

    @Provides
    static SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(BuildConfig.SETTINGS_FILE, Context.MODE_PRIVATE);
    }

    @Binds
    abstract LocationManager provideLocationManager(LocationProvider provider);

    @Singleton
    @Provides
    static GeoDataClient provideGeoDataClient(Context context) {
        return Places.getGeoDataClient(context, null);
    }

    @PerActivity
    @ContributesAndroidInjector
    abstract HomeActivity homeActivityInjector();

    @PerActivity
    @ContributesAndroidInjector
    abstract MapActivity mapActivityInjector();

    @PerActivity
    @ContributesAndroidInjector
    abstract DetailsActivity detailsActivityInjector();
}
