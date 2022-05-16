package com.example.daggerapplication.ui.main.posts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.daggerapplication.SessionManager;
import com.example.daggerapplication.di.main.PostsModule;
import com.example.daggerapplication.network.PostApi;
import com.example.daggerapplication.ui.main.posts.model.Post;
import com.example.daggerapplication.ui.main.profile.model.User;
import com.example.daggerapplication.util.NetworkResource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {

    private SessionManager sessionManager;
    private PostApi postApi;
    private MediatorLiveData<NetworkResource<List<Post>>> postsData ;


    @Inject
    public PostViewModel(SessionManager sessionManager, PostApi postApi) {
        this.sessionManager = sessionManager;
        this.postApi = postApi;
    }

    public LiveData<NetworkResource<List<Post>>> getPosts() {
        int userId = sessionManager.getAuthUser().getValue().data.getId();
        postsData = new MediatorLiveData<>();
        postsData.setValue(NetworkResource.loading((List<Post>) null));

        final LiveData<NetworkResource<List<Post>>> sources =  LiveDataReactiveStreams.fromPublisher(
                postApi.getPostsData(String.valueOf(userId))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorReturn(throwable -> {
                            List<Post> postList = new ArrayList<>();
                            Post post = new Post();
                            post.setId(-1);
                            post.setTitle(throwable.getMessage());
                            postList.add(post);
                            return postList;
                        })
                        .map(posts -> {
                            if(posts.size()> 0 && posts.get(0).getId() == -1){
                                return  NetworkResource.error(posts.get(0).getTitle());
                            }

                            return NetworkResource.success(posts);
                        })
        );

        postsData.addSource(sources, new Observer<NetworkResource<List<Post>>>() {
            @Override
            public void onChanged(NetworkResource<List<Post>> listNetworkResource) {
                postsData.setValue(listNetworkResource);
                postsData.removeSource(sources);
            }
        });

        return postsData ;

    }

}
