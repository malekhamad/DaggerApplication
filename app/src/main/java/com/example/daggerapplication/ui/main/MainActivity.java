package com.example.daggerapplication.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.daggerapplication.BaseActivity;
import com.example.daggerapplication.R;
import com.example.daggerapplication.ui.main.posts.PostsFragment;
import com.example.daggerapplication.ui.main.profile.ProfileFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout ;
    NavigationView navigationView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,new ProfileFragment())
                .commit();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onAuthSuccess() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,new ProfileFragment())
                        .commit();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_posts:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,new PostsFragment())
                        .commit();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }


        return true;
    }
}
