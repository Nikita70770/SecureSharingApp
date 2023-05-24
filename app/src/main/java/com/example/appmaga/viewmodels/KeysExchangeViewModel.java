package com.example.appmaga.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.AndroidViewModel;

import com.example.appmaga.cryptographic_algorithms.diffie_hellman.DHAlgorithm;
import com.example.appmaga.helpers.GsonWork;
import com.example.appmaga.model.repository.DataRepository;

public class KeysExchangeViewModel extends AndroidViewModel {

    private Context context;
    private DataRepository repository;
    private DHAlgorithm algorithm;

    public KeysExchangeViewModel(Application application) {
        super(application);
    }

    public void init(Context context){
        this.context = context;
        repository = new DataRepository(getApplication().getBaseContext());
        repository.setPreferencesInstance();
    }

    private void openWindowWithMessengers(String data){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        intent.setType("*/*");
        context.startActivity(Intent.createChooser(intent, "Share with friends"));
    }

    public void sendData(){
        if(algorithm != null) { algorithm = null; }
        setAlgorithm();

        String jsonDHAlgorithm = GsonWork.performSerialization(getAlgorithm());
        openWindowWithMessengers(jsonDHAlgorithm);
    }

    public void saveData(String data){
        if(algorithm != null) { algorithm = null; }
        setAlgorithm(data);

        String publicKeyContact = String.valueOf(algorithm.getPublicKeyUser());
        openWindowWithMessengers(publicKeyContact);
    }

    public void calcGeneralSecretKey(int value){
        algorithm.setValueKey(value);
        algorithm.calcGeneralSecretKey();
        repository.saveGeneralSecretKey(algorithm.getGeneralSecretKey());
    }

    public int getGeneralSecretKey(){
        return algorithm.getGeneralSecretKey();
    }

    public void setAlgorithm() {
        algorithm = new DHAlgorithm();
        algorithm.setSecretKey();
        algorithm.calcPublicKeyUser();
    }

    public DHAlgorithm getAlgorithm() {
        return algorithm;
    }


    public void setAlgorithm(String jsonData) {
        algorithm = (DHAlgorithm) GsonWork.performDeserialization(jsonData, DHAlgorithm.class.getSimpleName());
        algorithm.setSecretKey();
        algorithm.setPublicKeyContact(algorithm.getPublicKeyUser());
        algorithm.calcPublicKeyUser();
    }
}
