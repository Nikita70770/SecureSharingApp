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

    private void openWindowWithMessengers(String data){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        intent.setType("*/*");
        getApplication().startActivity(Intent.createChooser(intent, "Share with friends"));
    }

    public void sendData(){
        if(algorithm != null) { algorithm = null; }

        setAlgorithm();
        algorithm.setSecretKey();
        String jsonDHAlgorithm = GsonWork.performSerialization(DHAlgorithm.class);

        openWindowWithMessengers(jsonDHAlgorithm);
    }

    public DHAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm() {
        this.algorithm = new DHAlgorithm();
    }
}
