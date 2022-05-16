package com.example.daggerapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.daggerapplication.ui.main.profile.model.User;
import com.example.daggerapplication.util.NetworkResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkSessionStatus();

    }


    public abstract void showProgressBar();

    public abstract void hideProgressBar();

    public abstract void onAuthSuccess();

    private void checkSessionStatus(){
        sessionManager.getAuthUser().observe(this, new Observer<NetworkResource<User>>() {
            @Override
            public void onChanged(NetworkResource<User> networkResource) {
                switch (networkResource.status){
                    case LOADING:
                        showProgressBar();
                        break;

                    case SUCCESS:
                        hideProgressBar();
                        Log.i(TAG, "onCreate: Success");
                        onAuthSuccess();

                        break;

                    case ERROR:
                        hideProgressBar();
                        Log.i(TAG, "onCreate: Error");
                        break;

                    case EMPTY:
                        hideProgressBar();
                        Log.i(TAG, "onCreate: Empty");

                        break;

                    case NETWORK_ERROR:
                        hideProgressBar();
                        Log.i(TAG, "onCreate: Network Error");

                        break;
                }
            }
        });
    }
}
