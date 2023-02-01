package com.example.appmaga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appmaga.Fragments.AuthorizationFragment;
import com.example.appmaga.Fragments.MainFragment;
import com.example.appmaga.Interfaces.IAuthoFragmentListener;

public class MainActivity extends AppCompatActivity implements IAuthoFragmentListener {

    AuthorizationFragment authoFragment;
    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            authoFragment = new AuthorizationFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainer, authoFragment)
                    .commit();
        }
    }

    @Override
    public void onAuthoFragmentListener() {
        mainFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, mainFragment)
                .commit();
    }
}