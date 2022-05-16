package com.example.daggerapplication.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.daggerapplication.R;
import com.example.daggerapplication.ui.main.profile.model.User;
import com.example.daggerapplication.util.NetworkResource;
import com.example.daggerapplication.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = "ProfileFragment";

    TextView emailTextView ,usernameTextView, websiteTextView ;

    ProfileViewModel viewModel ;


    @Inject
    ViewModelProviderFactory providerFactory ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this,providerFactory).get(ProfileViewModel.class);

        emailTextView = view.findViewById(R.id.email);
        usernameTextView = view.findViewById(R.id.username);
        websiteTextView = view.findViewById(R.id.website);

        observeData();

        super.onViewCreated(view, savedInstanceState);
    }

    private void observeData(){
        viewModel.getAuthUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthUser().observe(getViewLifecycleOwner(), new Observer<NetworkResource<User>>() {
            @Override
            public void onChanged(NetworkResource<User> networkResource) {
                emailTextView.setText(networkResource.data.getEmail());
                usernameTextView.setText(networkResource.data.getUsername());
                usernameTextView.setText(String.valueOf(networkResource.data.getId()));

            }
        });
    }


}
