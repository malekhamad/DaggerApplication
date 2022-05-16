package com.example.daggerapplication.network;

import com.example.daggerapplication.ui.main.profile.model.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("users/{id}")
    Flowable<User> getSpecificUserDetail(@Path("id") String pathId);
}
