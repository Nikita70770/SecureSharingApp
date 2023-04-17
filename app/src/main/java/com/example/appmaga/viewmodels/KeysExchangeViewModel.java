package com.example.appmaga.viewmodels;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appmaga.encryption.diffie_hellman.DHAlgorithm;
import com.example.appmaga.helpers.GsonWork;
import com.example.appmaga.model.repository.DataRepository;

public class KeysExchangeViewModel extends AndroidViewModel {

    private DataRepository repository;
    private DHAlgorithm algorithm;

    public KeysExchangeViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        repository = new DataRepository(getApplication().getBaseContext());
    }

    public void sendData(){
        if(algorithm != null) { algorithm = null; }
        setAlgorithm();
    }

    public DHAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm() {
        this.algorithm = new DHAlgorithm();
    }
}
