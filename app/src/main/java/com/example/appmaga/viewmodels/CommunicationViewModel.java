package com.example.appmaga.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appmaga.model.repository.DataRepository;

public class CommunicationViewModel extends AndroidViewModel {

    private DataRepository repository;

    public void init(){
        this.repository = new DataRepository(getApplication().getBaseContext());
        this.repository.setPreferencesInstance();
    }

    public CommunicationViewModel(@NonNull Application application) {
        super(application);
    }
}
