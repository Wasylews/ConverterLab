package com.genius.wasylews.converterlab;

import com.genius.wasylews.converterlab.di.component.DaggerAppComponent;
import com.raizlabs.android.dbflow.config.FlowManager;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .create(this);
    }
}
