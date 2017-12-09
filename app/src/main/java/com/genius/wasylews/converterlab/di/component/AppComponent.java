package com.genius.wasylews.converterlab.di.component;

import com.genius.wasylews.converterlab.App;
import com.genius.wasylews.converterlab.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(App app);
}
