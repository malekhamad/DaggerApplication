package com.example.daggerapplication.di.main;

import androidx.lifecycle.ViewModel;

import com.example.daggerapplication.di.ViewModelKey;
import com.example.daggerapplication.ui.main.posts.PostViewModel;
import com.example.daggerapplication.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    abstract ViewModel bindPostViewModel(PostViewModel postViewModel);

}
