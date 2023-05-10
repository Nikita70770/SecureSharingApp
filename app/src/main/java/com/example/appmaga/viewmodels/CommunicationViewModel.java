package com.example.appmaga.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appmaga.model.repository.DataRepository;

public class CommunicationViewModel extends AndroidViewModel {

    private DataRepository repository;
    private Context context;

    public void init(){
        this.context = getApplication().getBaseContext();
        this.repository = new DataRepository(getApplication().getBaseContext());
        this.repository.setPreferencesInstance();
    }

    public CommunicationViewModel(@NonNull Application application) {
        super(application);
    }

    public void openWindowWithMessengers(String message){
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("*/*");
        context.startActivity(Intent.createChooser(intent, "Share with friends"));
    }
}
