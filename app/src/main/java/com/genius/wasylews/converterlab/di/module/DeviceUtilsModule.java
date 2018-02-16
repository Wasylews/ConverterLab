package com.genius.wasylews.converterlab.di.module;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.genius.wasylews.converterlab.BuildConfig;
import com.genius.wasylews.converterlab.R;
import com.genius.wasylews.device.bitmap.BitmapProviderUtil;
import com.genius.wasylews.device.location.LocationProvider;
import com.genius.wasylews.device.notification.NotificationUtil;
import com.genius.wasylews.domain.bitmap.BitmapProvider;
import com.genius.wasylews.domain.location.LocationManager;
import com.genius.wasylews.domain.notification.NotificationManagerUtil;
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
    BitmapProvider provideBitmapUtil(BitmapProviderUtil providerUtil);

    @Singleton
    @Provides
    static NotificationManager provideNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Singleton
    @Provides
    static NotificationCompat.Builder provideNotification(Context context,
                                                          NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel("default",
                    context.getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(channel);
        }

        return new NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.drawable.ic_launcher_background);
    }

    @Singleton
    @Binds
    NotificationManagerUtil provideNotificationManagerUtil(NotificationUtil manager);

}
