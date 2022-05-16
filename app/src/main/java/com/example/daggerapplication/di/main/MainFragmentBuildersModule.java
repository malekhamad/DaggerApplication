package com.example.daggerapplication.di.main;

import com.example.daggerapplication.ui.main.posts.PostsFragment;
import com.example.daggerapplication.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector(
            modules = {
                    MainViewModelModule.class
            }
    )
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector(
            modules = {
                    MainViewModelModule.class
            }
    )
    abstract PostsFragment contributePostsFragment();



}
