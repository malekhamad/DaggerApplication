package com.example.daggerapplication.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerapplication.R;
import com.example.daggerapplication.ui.main.posts.model.Post;
import com.example.daggerapplication.util.NetworkResource;
import com.example.daggerapplication.viewmodel.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private static final String TAG = "PostsFragment";

    private RecyclerView recyclerView ;

    @Inject
    PostsRecyclerAdapter postsAdapter ;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory ;

    PostViewModel viewModel ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_posts,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this,viewModelProviderFactory).get(PostViewModel.class);

        recyclerView = view.findViewById(R.id.recycler_view_posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(postsAdapter);

        subscribeObserver();


    }

    private void subscribeObserver(){
        viewModel.getPosts().removeObservers(getViewLifecycleOwner());
        viewModel.getPosts().observe(getViewLifecycleOwner(), listNetworkResource -> {
            switch (listNetworkResource.status){
                case EMPTY:
                    Log.i(TAG, "subscribeObserver: Empty");
                    break;
                case LOADING:
                    Log.i(TAG, "subscribeObserver: Loading");
                    break;
                case ERROR:
                    Log.i(TAG, "subscribeObserver: Error");
                    break;
                case NETWORK_ERROR:
                    Log.i(TAG, "subscribeObserver: Network Error");
                    break;
                default:
                    postsAdapter.setPostList(listNetworkResource.data);
                    Log.i(TAG, "subscribeObserver: Success");

                    break;
            }
        });
    }
}
