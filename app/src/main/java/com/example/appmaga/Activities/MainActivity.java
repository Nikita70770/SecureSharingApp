package com.example.appmaga.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.appmaga.R;
import com.example.appmaga.User.UserSettings;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private TextView txtLoginUser;

    private String loginUser, passwordUser;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDataUser();
        initElements();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initElements(){
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        navigationView = findViewById(R.id.navigationView);

        View headerView = MainActivity.this.navigationView.getHeaderView(0);
        txtLoginUser = headerView.findViewById(R.id.txtLoginUser);
        txtLoginUser.setText(loginUser);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setSupportActionBar(toolbar);
        navigationView.setItemIconTintList(null);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initDataUser(){
        loginUser = UserSettings.getUser().getLogin();
        passwordUser = UserSettings.getUser().getPassword();
    }
}
