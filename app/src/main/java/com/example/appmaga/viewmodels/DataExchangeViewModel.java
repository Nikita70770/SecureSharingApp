package com.example.appmaga.viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appmaga.R;
import com.example.appmaga.helpers.MathHelper;
import com.example.appmaga.model.entities.User;
import com.example.appmaga.model.repository.DataRepository;

public class DataExchangeViewModel extends AndroidViewModel {

    private DataRepository repository;

    public DataExchangeViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        this.repository = new DataRepository(getApplication().getBaseContext());
    }

    public User getUser(){
        return repository.getUser();
    }

    public boolean checkSendData(String login, String password){
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
            else{
                if(!getUser().getLogin().equals(login) ||
                        !getUser().getPassword().equals(MathHelper.getHash(password))){
                    Toast.makeText(getApplication(), R.string.toast_wrong_data_exchange, Toast.LENGTH_SHORT).show();
                    return false;
                }
                else
                    return true;
            }
        }
    }
}
