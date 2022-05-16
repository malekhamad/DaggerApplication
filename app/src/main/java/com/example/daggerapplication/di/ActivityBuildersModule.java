package com.example.daggerapplication.di;

import com.example.daggerapplication.di.auth.AuthModule;
import com.example.daggerapplication.di.auth.AuthScope;
import com.example.daggerapplication.di.auth.AuthViewModelModule;
import com.example.daggerapplication.di.main.MainFragmentBuildersModule;
import com.example.daggerapplication.di.main.MainScope;
import com.example.daggerapplication.di.main.PostsModule;
import com.example.daggerapplication.ui.auth.AuthActivity;
import com.example.daggerapplication.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class
            }
    )
    abstract AuthActivity authActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuildersModule.class,
                    PostsModule.class
            }
    )
    abstract MainActivity mainActivity();

}
