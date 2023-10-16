package com.example.appmaga.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.appmaga.R;
import com.example.appmaga.helpers.MathHelper;
import com.example.appmaga.model.entities.User;
import com.example.appmaga.model.repository.DataRepository;

public class DataExchangeViewModel extends ViewModel {

    private Context context;
    private DataRepository repository;

    public void init(Context context){
        this.context = context;
        this.repository = new DataRepository(context);
        repository.setPreferencesInstance();
    }

    public User getUser(){
        return repository.getUser();
    }

    public boolean checkSendData(String login, String password){
        if(login.isEmpty() && password.isEmpty()){
            Toast.makeText(context, R.string.toast_login_password, Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            if(login.isEmpty()){
                Toast.makeText(context, R.string.toast_login, Toast.LENGTH_SHORT).show();
                return false;
            }
            else if(password.isEmpty()){
                Toast.makeText(context, R.string.toast_password, Toast.LENGTH_SHORT).show();
                return false;
            }
            else{
                if(!getUser().getLogin().equals(login) ||
                        !getUser().getPassword().equals(MathHelper.getHash(password))){
                    Toast.makeText(context, R.string.toast_wrong_data_exchange, Toast.LENGTH_SHORT).show();
                    return false;
                }
                else
                    return true;
            }
        }
    }

    public void openWindowWithMessengers(String data){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        intent.setType("*/*");
        context.startActivity(Intent.createChooser(intent, "Share with friends"));
    }
}
