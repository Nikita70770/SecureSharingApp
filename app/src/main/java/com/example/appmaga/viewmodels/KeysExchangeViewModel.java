package com.example.appmaga.viewmodels;

import android.app.Application;
import android.content.Context;
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

    private void openWindowWithMessengers(Context context, String data){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        intent.setType("*/*");
        context.startActivity(Intent.createChooser(intent, "Share with friends"));
    }

    public void sendData(Context context){
        if(algorithm != null) { algorithm = null; }

        setAlgorithm();
        algorithm.setSecretKey();
        algorithm.calcPublicKeyUser();
        String jsonDHAlgorithm = GsonWork.performSerialization(getAlgorithm());

        openWindowWithMessengers(context, jsonDHAlgorithm);
    }

    public void saveData(String data){
        if(algorithm != null) { algorithm = null; }

        setAlgorithm(data);
        algorithm.setSecretKey();
    }

    public void setAlgorithm() {
        algorithm = new DHAlgorithm();
    }

    public DHAlgorithm getAlgorithm() {
        return algorithm;
    }


    public void setAlgorithm(String data) {
        algorithm = (DHAlgorithm) GsonWork.performDeserialization(data, DHAlgorithm.class.getSimpleName());
    }
}
