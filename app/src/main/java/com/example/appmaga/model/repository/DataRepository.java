package com.example.appmaga.model.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

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

    public DataRepository(ContactDAO dao){ this.contactDAO = dao; }

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


    public void saveGeneralSecretKey(int key){
        storage.saveGeneralSecretKey(key);
    }
    public int getGeneralSecretKey(){
        return storage.getGeneralSecretKey();
    }

    public void addContact(Contact contact){
        new AddContactTask(contactDAO).execute(contact);
    }

    public LiveData<List<Contact>> getAllContacts(){
        return contactDAO.getAllContacts();
    }

    private class AddContactTask extends AsyncTask<Contact, Void, Void> {
        private ContactDAO contactDAO;

        public AddContactTask(ContactDAO dao){
            this.contactDAO = dao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDAO.addContact(contacts[0]);
            return null;
        }
    }
}
