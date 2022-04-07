package com.example.daggerapplication.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.daggerapplication.ui.auth.AuthViewModel;
import com.example.daggerapplication.viewmodel.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);



}
