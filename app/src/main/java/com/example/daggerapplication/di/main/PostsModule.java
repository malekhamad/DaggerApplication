package com.example.daggerapplication.di.main;

import com.example.daggerapplication.network.PostApi;
import com.example.daggerapplication.ui.main.posts.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class PostsModule {

    @MainScope
    @Provides
    static PostApi providePostApi(Retrofit retrofit){
        return retrofit.create(PostApi.class);
    }

    @MainScope
    @Provides
    static PostsRecyclerAdapter provideRecyclerAdapter(){
        return new PostsRecyclerAdapter() ;
    }

}
