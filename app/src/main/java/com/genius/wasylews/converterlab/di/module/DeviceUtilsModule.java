package com.genius.wasylews.converterlab.di.module;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;

import com.genius.wasylews.converterlab.BuildConfig;
import com.genius.wasylews.device.bitmap.BitmapProviderUtil;
import com.genius.wasylews.device.location.LocationProvider;
import com.genius.wasylews.domain.bitmap.BitmapProvider;
import com.genius.wasylews.domain.location.LocationManager;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = { NetworkModule.class })
public interface DeviceUtilsModule {

    @Provides
    static SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(BuildConfig.SETTINGS_FILE, Context.MODE_PRIVATE);
    }

    @Binds
    LocationManager provideLocationManager(LocationProvider provider);

    @Singleton
    @Provides
    static GeoDataClient provideGeoDataClient(Context context) {
        return Places.getGeoDataClient(context, null);
    }

    @Provides
    static AlarmManager provideAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    @Singleton
    @Binds
    abstract BitmapProvider provideBitmapUtil(BitmapProviderUtil providerUtil);
}
