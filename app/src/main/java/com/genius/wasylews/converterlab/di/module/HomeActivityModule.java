package com.genius.wasylews.converterlab.di.module;

import android.content.Context;

import com.genius.wasylews.converterlab.view.activity.HomeActivity;

import dagger.Binds;
import dagger.Module;

@Module
public interface HomeActivityModule {

    @Binds
    Context provideContext(HomeActivity activity);
}
