package com.example.appmaga.Interfaces;

import com.example.appmaga.Authentication.Chap;
import com.example.appmaga.model.entities.Contact;

public interface IMainActivityListener {
    void getContact(Contact contact);
    void sendChap(Chap chap);
    void showFragment();
}
