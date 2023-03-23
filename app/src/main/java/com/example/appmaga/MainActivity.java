package com.example.appmaga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.appmaga.Fragments.LoginFragment;
import com.example.appmaga.Fragments.MainFragment;
import com.example.appmaga.Fragments.RegistrationFragment;
import com.example.appmaga.Interfaces.ILoginFragmentListener;
import com.example.appmaga.Interfaces.IRegisterFragmentListener;

public class MainActivity extends AppCompatActivity implements IRegisterFragmentListener, ILoginFragmentListener {

    RegistrationFragment registrationFragment;
    LoginFragment loginFragment;
    MainFragment mainFragment;

    private SharedPreferences settings;
    private SharedPreferences.Editor editorSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        settings = getSharedPreferences(UserSettings.APP_PREFERENCES, UserSettings.ACCESS_MODE);
        editorSettings = settings.edit();

        if(savedInstanceState == null){
            registrationFragment = new RegistrationFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.formFragmentContainer, registrationFragment)
                    .commit();
        }
    }

    @Override
    public void onRegisterListener(String loginName, String password) {
        editorSettings.putString(UserSettings.APP_PREFERENCES_LOGIN_NAME, loginName);
        editorSettings.putString(UserSettings.APP_PREFERENCES_PASSWORD, password);
        editorSettings.apply();
        mainFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragmentContainer, mainFragment)
                .commit();
    }

    @Override
    public void onRegisterSignInListener() {
        loginFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.formFragmentContainer, loginFragment)
                .commit();
    }

    @Override
    public void onLoginListener() {
        mainFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFragmentContainer, mainFragment)
                .commit();
    }

    @Override
    public void onLoginSignUpListener() {
        registrationFragment = new RegistrationFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.formFragmentContainer, registrationFragment)
                .commit();
    }
}