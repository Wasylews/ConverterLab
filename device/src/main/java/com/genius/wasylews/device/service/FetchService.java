package com.genius.wasylews.device.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.genius.wasylews.domain.repository.Repository;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class FetchService extends Service {

    @Inject
    Repository repository;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        repository.fetchOrganizations();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new FetchServiceBinder();
    }

    class FetchServiceBinder extends Binder {
        public FetchService getService() {
            return FetchService.this;
        }
    }
}
