package com.genius.wasylews.converterlab.di.component;

import com.genius.wasylews.converterlab.App;
import com.genius.wasylews.converterlab.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
