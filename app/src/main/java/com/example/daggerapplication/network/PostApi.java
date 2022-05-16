package com.example.daggerapplication.network;

import com.example.daggerapplication.ui.main.posts.model.Post;
import com.example.daggerapplication.ui.main.profile.model.User;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostApi {

    @GET("posts")
    Flowable<List<Post>> getPostsData(@Query("user_id") String userId);
}
