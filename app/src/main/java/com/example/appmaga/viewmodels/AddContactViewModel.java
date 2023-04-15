package com.example.appmaga.viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appmaga.R;
import com.example.appmaga.model.dao.ContactDAO;
import com.example.appmaga.model.db.AppDatabase;
import com.example.appmaga.model.entities.Contact;
import com.example.appmaga.model.repository.DataRepository;

public class AddContactViewModel extends AndroidViewModel {

    private ContactDAO contactDAO;
    private DataRepository repository;

    public AddContactViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        this.contactDAO = AppDatabase.getInstance(getApplication()).contactDAO();
        this.repository = new DataRepository(contactDAO);
    }

    public void addContact(Contact contact){
        repository.addContact(contact);
    }

    public boolean checkGetData(String data){
        if(data.isEmpty()){
            Toast.makeText(getApplication(), R.string.toast_login_password, Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }
}
