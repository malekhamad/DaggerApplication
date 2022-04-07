package com.example.daggerapplication.ui.auth;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.RequestManager;
import com.example.daggerapplication.BaseActivity;
import com.example.daggerapplication.R;
import com.example.daggerapplication.ui.main.MainActivity;
import com.example.daggerapplication.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends BaseActivity {
    private static final String TAG = "AuthActivity";

    private EditText editTextUserId ;
    private Button loginBtn ;
    private AuthViewModel viewModel ;
    private ProgressBar progressBar ;

    @Inject
    ViewModelProviderFactory providerFactory ;

    @Inject
    Drawable logo ;

    @Inject
    RequestManager requestManager ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewModel = new ViewModelProvider(this,providerFactory).get(AuthViewModel.class);

        progressBar = findViewById(R.id.progress_bar);
        editTextUserId = findViewById(R.id.user_id_input);
        loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener((view -> {
            viewModel.authenticateFromId(editTextUserId.getText().toString());
        }));

        setLogo();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onAuthSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }
}