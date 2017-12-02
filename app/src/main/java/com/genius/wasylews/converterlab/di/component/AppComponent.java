package com.genius.wasylews.converterlab.di.component;

import com.genius.wasylews.converterlab.App;
import com.genius.wasylews.converterlab.di.module.AppModule;

import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(App app);
}
