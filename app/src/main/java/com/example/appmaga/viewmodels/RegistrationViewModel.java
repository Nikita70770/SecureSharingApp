package com.example.appmaga.viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appmaga.R;
import com.example.appmaga.model.entities.User;
import com.example.appmaga.model.repository.DataRepository;

public class RegistrationViewModel extends AndroidViewModel {

    private DataRepository repository;

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        this.repository = new DataRepository(getApplication().getBaseContext());
        this.repository.setPreferencesInstance();
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
        repository.saveUser(user);
    }

    public User getUser(){
        return repository.getUser();
    }
}
