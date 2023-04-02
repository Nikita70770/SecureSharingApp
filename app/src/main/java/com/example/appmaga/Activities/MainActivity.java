package com.example.appmaga.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.appmaga.Contact.Contact;
import com.example.appmaga.DialogFragments.AddContactFragment;
import com.example.appmaga.DialogFragments.DataExchangeFragment;
import com.example.appmaga.File.FileWork;
import com.example.appmaga.Fragments.ChatsFragment;
import com.example.appmaga.Gson.GsonWork;
import com.example.appmaga.Interfaces.IAddContactFragmentListener;
import com.example.appmaga.Interfaces.IChatsFragmentListener;
import com.example.appmaga.Interfaces.IMainActivityListener;
import com.example.appmaga.R;
import com.example.appmaga.User.UserSettings;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivityListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private TextView txtLoginUser, txtListChats;

    private ChatsFragment chatsFragment;

    private IAddContactFragmentListener addContactFragmentListener;
    private IChatsFragmentListener chatsFragmentListener;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initElements();
        showAllChats();
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
                AddContactFragment fragment = AddContactFragment.newInstance();
                addContactFragmentListener = (IAddContactFragmentListener) fragment;
                addContactFragmentListener.initFileWork(getFileWork());
                showDialogFragment(fragment, AddContactFragment.ID_DIALOG_ADD_CONTACT);
                return true;

            default:
                return false;
        }
    }

    private void initElements(){
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        txtListChats = findViewById(R.id.txtListChats);
        txtLoginUser = this.navigationView.getHeaderView(0).findViewById(R.id.txtLoginUser);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_item_nav_open, R.string.menu_item_nav_close);

        txtLoginUser.setText(UserSettings.getUser().getLogin());
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

            case AddContactFragment.ID_DIALOG_ADD_CONTACT:
                fragment.show(getSupportFragmentManager(), "addContactFragment");
                break;
        }
    }

    private void showAllChats(){
        List<String> list = new ArrayList<>(getFileWork().readAllLinesInternalFile());
        if(list.size() != 0){
            txtListChats.setVisibility(View.GONE);
            chatsFragment = ChatsFragment.newInstance(list);
            addFragment(R.id.chatsFragmentContainer, chatsFragment);
        }
    }

    private void addFragment(int idElement, Fragment fragment){
        fragmentManager
                .beginTransaction()
                .add(idElement, fragment)
                .commit();
    }

    public FileWork getFileWork() {
        return new FileWork(getApplicationContext(), FileWork.FILE_NAME);
    }

    @Override
    public void getContact(String data) {
        Contact contact = (Contact) GsonWork.performDeserialization(data, Contact.class.getSimpleName());
        chatsFragmentListener = (IChatsFragmentListener) chatsFragment;
        chatsFragmentListener.sendContact(contact);
    }
}
