package com.example.appmaga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.appmaga.Fragments.LoginFragment;
import com.example.appmaga.Fragments.MainFragment;
import com.example.appmaga.Fragments.RegistrationFragment;
import com.example.appmaga.Interfaces.ILoginFragmentListener;
import com.example.appmaga.Interfaces.IRegisterFragmentListener;
import com.example.appmaga.User.User;
import com.example.appmaga.User.UserSettings;

public class MainActivity extends AppCompatActivity implements IRegisterFragmentListener, ILoginFragmentListener {

    private RegistrationFragment registrationFragment;
    private LoginFragment loginFragment;
    private MainFragment mainFragment;

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
    public void onRegisterListener(User user) {
        if(UserSettings.getUser(settings) != null){
            Toast.makeText(getApplicationContext(), R.string.register_msg_1, Toast.LENGTH_SHORT).show();
        }
        else{
            UserSettings.saveUser(editorSettings, user);
            hideFragment(registrationFragment);
            closeSystemKeyboard();
            mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainFragmentContainer, mainFragment)
                    .commit();
            Toast.makeText(this, R.string.register_msg_2, Toast.LENGTH_SHORT).show();
        }
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
    public void onLoginListener(String loginName, String password) {
        User savedUser = UserSettings.getUser(settings);
        if(savedUser == null){
            Toast.makeText(this, R.string.login_msg_1, Toast.LENGTH_SHORT).show();
        }
        else{
            if(!savedUser.getLogin().equals(loginName) || !savedUser.getPassword().equals(password)){
                Toast.makeText(this, R.string.login_msg_2, Toast.LENGTH_SHORT).show();
            }
            else{
                hideFragment(loginFragment);
                closeSystemKeyboard();
                mainFragment = new MainFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainFragmentContainer, mainFragment)
                        .commit();
                Toast.makeText(this, R.string.login_msg_3, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLoginSignUpListener() {
        registrationFragment = new RegistrationFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.formFragmentContainer, registrationFragment)
                .commit();
    }

    private void closeSystemKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void hideFragment(Fragment  fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .hide(fragment)
                .commit();
    }
}