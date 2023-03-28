package com.example.appmaga.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.appmaga.DialogFragments.DataExchangeFragment;
import com.example.appmaga.R;
import com.example.appmaga.User.UserSettings;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private View headerView;
    private DialogFragment dataExchangeFragment, addContactFragment;
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

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navExchangeData:
                dataExchangeFragment = new DataExchangeFragment();
                showDialogFragment(dataExchangeFragment, DataExchangeFragment.ID_FRAGMENT_DATA_EXCHANGE);
                return true;

            case R.id.navAddContact:
                //
                return true;

            default:
                return false;
        }
    }

    private void initElements(){
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        headerView = MainActivity.this.navigationView.getHeaderView(0);
        txtLoginUser = headerView.findViewById(R.id.txtLoginUser);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        txtLoginUser.setText(loginUser);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void showDialogFragment(DialogFragment fragment, int fragmentId){
        switch(fragmentId){
            case DataExchangeFragment.ID_FRAGMENT_DATA_EXCHANGE:
                fragment.show(getSupportFragmentManager(), "dataExchangeFragment");
                break;
        }
    }

    private void initDataUser(){
        loginUser = UserSettings.getUser().getLogin();
        passwordUser = UserSettings.getUser().getPassword();
    }
}
