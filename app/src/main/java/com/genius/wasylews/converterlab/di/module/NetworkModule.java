package com.genius.wasylews.converterlab.di.module;

import android.content.Context;
import android.net.ConnectivityManager;

import com.genius.wasylews.device.network.NetworkUtils;
import com.genius.wasylews.domain.network.NetworkManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class NetworkModule {

    @Provides
    static ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Binds
    abstract NetworkManager provideNetworkManager(NetworkUtils manager);
}
