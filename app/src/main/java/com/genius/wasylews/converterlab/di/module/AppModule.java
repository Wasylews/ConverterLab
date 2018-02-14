package com.genius.wasylews.converterlab.di.module;

import android.content.Context;

import com.genius.wasylews.converterlab.App;
import com.genius.wasylews.converterlab.di.scope.PerActivity;
import com.genius.wasylews.converterlab.di.scope.PerFragment;
import com.genius.wasylews.converterlab.view.activity.DetailsActivity;
import com.genius.wasylews.converterlab.view.activity.HomeActivity;
import com.genius.wasylews.converterlab.view.activity.MapActivity;
import com.genius.wasylews.converterlab.view.fragment.ShareDialogFragment;
import com.genius.wasylews.device.service.FetchService;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = { AndroidSupportInjectionModule.class,
        RepositoryModule.class, DeviceUtilsModule.class })
public interface AppModule {

    @Binds
    Context provideApplicationContext(App app);

    @PerActivity
    @ContributesAndroidInjector
    HomeActivity homeActivityInjector();

    @PerActivity
    @ContributesAndroidInjector
    MapActivity mapActivityInjector();

    @PerActivity
    @ContributesAndroidInjector
    DetailsActivity detailsActivityInjector();

    @PerFragment
    @ContributesAndroidInjector
    ShareDialogFragment shareDialogFragmentInjector();

    @ContributesAndroidInjector
    FetchService fetchServiceInjector();
}
