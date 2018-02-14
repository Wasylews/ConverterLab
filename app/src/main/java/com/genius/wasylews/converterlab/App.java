package com.genius.wasylews.converterlab;

import android.app.AlarmManager;
import android.app.PendingIntent;

import com.genius.wasylews.converterlab.di.component.DaggerAppComponent;
import com.genius.wasylews.device.service.FetchService;
import com.raizlabs.android.dbflow.config.FlowManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


public class App extends DaggerApplication {

    @Inject
    AlarmManager mAlarmManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // configure dbflow
        FlowManager.init(this);

        // configure service alarm
        setupAlarm();
    }

    private void setupAlarm() {
        PendingIntent intent = FetchService.newIntent(this);

        mAlarmManager.setInexactRepeating(AlarmManager.RTC,
                System.currentTimeMillis() + AlarmManager.INTERVAL_HALF_HOUR,
                AlarmManager.INTERVAL_HALF_HOUR, intent);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .create(this);
    }
}
