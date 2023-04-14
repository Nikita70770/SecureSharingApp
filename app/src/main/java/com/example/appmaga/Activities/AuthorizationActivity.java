package com.example.appmaga.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.appmaga.File.FileWork;
import com.example.appmaga.Fragments.LoginFragment;
import com.example.appmaga.Fragments.RegistrationFragment;
import com.example.appmaga.Interfaces.ILoginFragmentListener;
import com.example.appmaga.Interfaces.IRegisterFragmentListener;
import com.example.appmaga.R;
import com.example.appmaga.model.entities.User;
import com.example.appmaga.User.UserSettings;

public class AuthorizationActivity extends AppCompatActivity implements IRegisterFragmentListener, ILoginFragmentListener {
    private RegistrationFragment registrationFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    private SharedPreferences settings;
    private SharedPreferences.Editor editorSettings;

    private FileWork fileWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        settings = getSharedPreferences(UserSettings.APP_PREFERENCES, UserSettings.ACCESS_MODE);
        editorSettings = settings.edit();
        fileWork = new FileWork(getApplicationContext(), FileWork.FILE_NAME);

        if(savedInstanceState == null){
            registrationFragment = RegistrationFragment.newInstance();
            addFragment(R.id.formFragmentContainer, registrationFragment);
        }
    }

    @Override
    public void onRegisterListener(User user) {
        UserSettings.requestUser(settings);
        if(UserSettings.getUser() != null){
            Toast.makeText(getApplicationContext(), R.string.toast_registered, Toast.LENGTH_SHORT).show();
        }
        else{
            UserSettings.saveUser(editorSettings, user);
            UserSettings.requestUser(settings);

            fileWork.createNewInternalFile();
            closeSystemKeyboard();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, R.string.toast_successful_registered, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegisterSignInListener() {
        replaceFragment(R.id.formFragmentContainer, LoginFragment.newInstance());
    }

    @Override
    public void onLoginListener(String loginName, String password) {
        UserSettings.requestUser(settings);
        if(UserSettings.getUser() == null){
            Toast.makeText(this, R.string.toast_not_registered, Toast.LENGTH_SHORT).show();
        }
        else{
            if(!UserSettings.getUser().getLogin().equals(loginName) || !UserSettings.getUser().getPassword().equals(password)){
                Toast.makeText(this, R.string.toast_wrong_login_or_password, Toast.LENGTH_SHORT).show();
            }
            else{
                closeSystemKeyboard();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, R.string.toast_successful_authorization, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLoginSignUpListener() {
        registrationFragment = new RegistrationFragment();
        replaceFragment(R.id.formFragmentContainer, registrationFragment);
    }

    private void closeSystemKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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