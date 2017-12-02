package com.genius.wasylews.converterlab;

import android.app.Application;

import com.genius.wasylews.converterlab.di.component.DaggerAppComponent;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.create().inject(this);
    }
}
