package com.example.appmaga.view.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.appmaga.view.dialog_fragments.AddContactFragment;
import com.example.appmaga.view.dialog_fragments.DataExchangeFragment;
import com.example.appmaga.view.fragments.ChatsFragment;
import com.example.appmaga.view.fragments.MainFragment;
import com.example.appmaga.interfaces.IChatsFragmentListener;
import com.example.appmaga.interfaces.IMainActivityListener;
import com.example.appmaga.R;
import com.example.appmaga.model.preferences.PreferencesStorage;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements IMainActivityListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TextView txtLoginUser;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private IChatsFragmentListener chatsFragmentListener;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initElements();
        initChatsFragment();
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
                showDialogFragment(DataExchangeFragment.newInstance(), DataExchangeFragment.ID_FRAGMENT_DATA_EXCHANGE);
                return true;

            case R.id.navAddContact:
                showDialogFragment(AddContactFragment.newInstance(), AddContactFragment.ID_DIALOG_ADD_CONTACT);
                return true;

            default:
                return false;
        }
    }

    private void initElements(){
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        txtLoginUser = this.navigationView.getHeaderView(0).findViewById(R.id.txtLoginUser);

        txtLoginUser.setText(PreferencesStorage.init(getBaseContext()).getUser().getLogin());
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_item_nav_open, R.string.menu_item_nav_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

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

            case AddContactFragment.ID_DIALOG_ADD_CONTACT:
                fragment.show(getSupportFragmentManager(), "addContactFragment");
                break;
        }
    }

    private void initChatsFragment(){
        addFragment(R.id.chatsFragmentContainer, ChatsFragment.newInstance());
    }

    private void addFragment(int idElement, Fragment fragment){
        fragmentManager
                .beginTransaction()
                .replace(idElement, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showFragment() {
        addFragment(R.id.chatsFragmentContainer, MainFragment.newInstance());
    }
}
