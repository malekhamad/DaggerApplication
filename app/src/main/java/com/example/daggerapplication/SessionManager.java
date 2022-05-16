package com.example.daggerapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.daggerapplication.ui.main.profile.model.User;
import com.example.daggerapplication.util.NetworkResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";

    private MediatorLiveData<NetworkResource<User>> cachedData = new MediatorLiveData<>();

    @Inject
    public SessionManager(){

    }

    public void authenticateWithID(final LiveData<NetworkResource<User>> source){
        if(cachedData != null){
            cachedData.setValue(NetworkResource.loading((User) null));
            cachedData.addSource(source, networkResource -> {
                cachedData.setValue(NetworkResource.success((User) networkResource.data));
                cachedData.removeSource(source);
            });

        }
    }

    public LiveData<NetworkResource<User>> getAuthUser(){
        return cachedData ;
    }
}
