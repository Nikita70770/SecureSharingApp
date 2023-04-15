package com.example.appmaga.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.appmaga.model.dao.ContactDAO;
import com.example.appmaga.model.db.AppDatabase;
import com.example.appmaga.model.entities.Contact;
import com.example.appmaga.model.repository.DataRepository;

import java.util.List;

public class ChatsViewModel extends AndroidViewModel {
    private ContactDAO contactDAO;
    private DataRepository repository;

    public ChatsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        this.contactDAO = AppDatabase.getInstance(getApplication()).contactDAO();
        this.repository = new DataRepository(contactDAO);
    }

    public List<Contact> getAllContacts(){
        return repository.getAllContacts();
    }
}
