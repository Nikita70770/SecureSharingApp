package com.example.appmaga.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appmaga.model.entities.Contact;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    void addContact(Contact contact);

    @Query("SELECT * FROM contacts")
    List<Contact> getAllContacts();
}
