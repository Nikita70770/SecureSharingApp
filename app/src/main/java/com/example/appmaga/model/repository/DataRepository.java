package com.example.appmaga.model.repository;

import android.content.Context;

import com.example.appmaga.model.dao.ContactDAO;
import com.example.appmaga.model.db.AppDatabase;
import com.example.appmaga.model.entities.Contact;
import com.example.appmaga.model.entities.User;
import com.example.appmaga.model.preferences.PreferencesStorage;

import java.util.List;

public class DataRepository {

    private Context context;
    private PreferencesStorage storage;
    private ContactDAO contactDAO;

    public DataRepository(ContactDAO dao){
        this.contactDAO = dao;
    }

    public DataRepository(Context context){
        this.context = context;
    }

    public void setPreferencesInstance(){
        this.storage = PreferencesStorage.init(context);
    }

    public void saveUser(User user){
        storage.saveUser(user);
    }

    public User getUser(){
        return storage.getUser();
    }

    public void addContact(Contact contact){
        contactDAO.addContact(contact);
    }

    public List<Contact> getAllContacts(){
        return contactDAO.getAllContacts();
    }
}
