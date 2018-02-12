package com.genius.wasylews.device.service;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.genius.wasylews.domain.repository.Repository;

import javax.inject.Inject;

import dagger.android.DaggerService;

public class FetchService extends DaggerService {

    private static final String TAG = FetchService.class.getSimpleName();
    @Inject
    Repository repository;

    public static PendingIntent newIntent(Context context) {
        Intent intent = new Intent(context, FetchService.class);

        return PendingIntent.getService(context,
                0, intent, 0);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Running service");
        repository.fetchOrganizations().subscribe(this::stopSelf);
        return START_STICKY;
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
