package com.example.appmaga.viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appmaga.R;
import com.example.appmaga.helpers.MathHelper;
import com.example.appmaga.model.entities.User;
import com.example.appmaga.model.preferences.PreferencesStorage;
import com.example.appmaga.model.repository.DataRepository;

public class LoginViewModel extends AndroidViewModel {

    private DataRepository repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        repository = new DataRepository(getApplication());
        repository.setPreferencesInstance();
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
                if (getUser() == null) {
                    Toast.makeText(getApplication(), R.string.toast_not_registered, Toast.LENGTH_SHORT).show();
                    return false;
                }
                else{
                    if(!getUser().getLogin().equals(login) || !getUser().getPassword().equals(getHashPassword(password))){
                        Toast.makeText(getApplication(), R.string.toast_wrong_login_or_password, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    else{
                        Toast.makeText(getApplication(), R.string.toast_successful_authorization, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
            }
        }
    }

    public User getUser(){
        return repository.getUser();
    }

    private String getHashPassword(String password){
        return new MathHelper().getHash(password);
    }
}
