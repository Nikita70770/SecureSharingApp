package com.example.appmaga.viewmodels;

import android.content.Context;
import android.content.Intent;
import androidx.lifecycle.ViewModel;
import com.example.appmaga.model.repository.DataRepository;

public class CommunicationViewModel extends ViewModel {

    private DataRepository repository;
    private Context context;

    public void init(Context context){
        this.context = context;
        this.repository = new DataRepository(context);
        this.repository.setPreferencesInstance();
    }

    public int getGeneralSecretKey(){
        return repository.getGeneralSecretKey();
    }

    public void openWindowWithMessengers(String data){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        intent.setType("*/*");
        context.startActivity(Intent.createChooser(intent, "Share with friends"));
    }
}
