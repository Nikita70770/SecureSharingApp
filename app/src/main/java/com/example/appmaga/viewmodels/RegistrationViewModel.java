package com.example.appmaga.viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appmaga.R;
import com.example.appmaga.model.entities.User;
import com.example.appmaga.model.preferences.PreferencesStorage;

public class RegistrationViewModel extends AndroidViewModel {

    private PreferencesStorage preferencesStorage;

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        this.preferencesStorage = PreferencesStorage.init(getApplication());
    }

    public boolean checkData(String login, String password){
        if(login.isEmpty() && password.isEmpty()){
            Toast.makeText(getApplication(), R.string.toast_login_password, Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            if(login.isEmpty()){
                Toast.makeText(getApplication(), R.string.toast_login, Toast.LENGTH_SHORT).show();
                return false;
            }
            else if(password.isEmpty()){
                Toast.makeText(getApplication(), R.string.toast_password, Toast.LENGTH_SHORT).show();
                return false;
            }
            else {
                if (getUser() != null) {
                    Toast.makeText(getApplication(), R.string.toast_registered, Toast.LENGTH_SHORT).show();
                    return false;
                }
                else{
                    Toast.makeText(getApplication(), R.string.toast_successful_registered, Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
    }

    public void saveUser(User user){
        preferencesStorage.saveUser(user);
    }

    public User getUser(){
        return preferencesStorage.getUser();
    }
}
