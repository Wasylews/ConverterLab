package com.genius.wasylews.converterlab.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.genius.wasylews.converterlab.App;
import com.genius.wasylews.converterlab.di.scope.PerActivity;
import com.genius.wasylews.converterlab.view.activity.HomeActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class,
        HomeActivityModule.class,
        RepositoryModule.class})
public abstract class AppModule {

    private static final String SETTINGS_FILE = "settings";

    @PerActivity
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity homeActivityInjector();

    @Binds
    abstract Application application(App app);

    @Provides
    static SharedPreferences provideSharedPreferences(Application context) {
        return context.getSharedPreferences(SETTINGS_FILE, Context.MODE_PRIVATE);
    }
}
