package com.example.daggerapplication.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggerapplication.SessionManager;
import com.example.daggerapplication.model.User;
import com.example.daggerapplication.network.AuthApi;
import com.example.daggerapplication.util.NetworkResource;


import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private final AuthApi authApi ;

    private static final String TAG = "AuthViewModel";
    public SessionManager sessionManager ;

    @Inject
    public AuthViewModel(AuthApi authApi,SessionManager sessionManager) {
       this.authApi = authApi;
       this.sessionManager = sessionManager ;
    }

    public void authenticateFromId(String id){
        sessionManager.authenticateWithID(queryUserId(id));
    }

    private LiveData<NetworkResource<User>> queryUserId(String userId){
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getSpecificUserDetail(userId)
                        .onErrorReturn(throwable -> {
                            User errorUser = new User();
                            errorUser.setId(-1);
                            return errorUser;
                        })
                        .map((Function<User, NetworkResource<User>>) user -> {
                            if(user.getId() == -1){
                                return NetworkResource.error("Could not authenticate");
                            }
                            return NetworkResource.success((User)user);
                        })
                        .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<NetworkResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }

}
