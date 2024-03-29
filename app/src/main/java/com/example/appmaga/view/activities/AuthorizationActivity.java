package com.example.appmaga.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import com.example.appmaga.view.fragments.LoginFragment;
import com.example.appmaga.view.fragments.RegistrationFragment;
import com.example.appmaga.interfaces.ILoginFragment;
import com.example.appmaga.interfaces.IRegisterFragment;
import com.example.appmaga.R;

public class AuthorizationActivity extends AppCompatActivity implements IRegisterFragment, ILoginFragment {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(savedInstanceState == null){
            addFragment(R.id.formFragmentContainer, RegistrationFragment.newInstance());
        }
        intent = new Intent(getApplicationContext(), MainActivity.class);
    }

    @Override
    public void onRegisterListener() {
        startActivity(intent);
    }

    @Override
    public void onRegisterSignInListener() {
        replaceFragment(R.id.formFragmentContainer, LoginFragment.newInstance());
    }

    @Override
    public void onLoginListener() {
        startActivity(intent);
    }

    @Override
    public void onLoginSignUpListener() {
        replaceFragment(R.id.formFragmentContainer, RegistrationFragment.newInstance());
    }

    private void addFragment(int idElement, Fragment fragment){
        fragmentManager
                .beginTransaction()
                .add(idElement, fragment)
                .commit();
    }

    private void replaceFragment(int idElement, Fragment fragment){
        fragmentManager
                .beginTransaction()
                .replace(idElement, fragment)
                .commit();
    }
}