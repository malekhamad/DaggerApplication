package com.example.daggerapplication.ui.main.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggerapplication.SessionManager;
import com.example.daggerapplication.ui.main.profile.model.User;
import com.example.daggerapplication.util.NetworkResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";

    private SessionManager sessionManager ;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager ;
    }

    public LiveData<NetworkResource<User>> getAuthUser(){
        return sessionManager.getAuthUser();
    }



}
