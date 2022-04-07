package com.example.daggerapplication.di;

import com.example.daggerapplication.di.auth.AuthModule;
import com.example.daggerapplication.di.auth.AuthViewModelModule;
import com.example.daggerapplication.ui.auth.AuthActivity;
import com.example.daggerapplication.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class
            }
    )
    abstract AuthActivity authActivity();

    @ContributesAndroidInjector()
    abstract MainActivity mainActivity();

}
