package com.genius.wasylews.device.network;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.genius.wasylews.domain.network.NetworkManager;

import javax.inject.Inject;

public class NetworkUtils implements NetworkManager {

    private final ConnectivityManager mConnectivityManager;

    @Inject
    NetworkUtils(ConnectivityManager manager) {
        mConnectivityManager = manager;
    }

    @Override
    public boolean isConnected() {
        NetworkInfo netInfo = mConnectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
