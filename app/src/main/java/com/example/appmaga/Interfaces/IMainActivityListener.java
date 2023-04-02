package com.example.appmaga.Interfaces;

import com.example.appmaga.Authentication.Chap;
import com.example.appmaga.Contact.Contact;

public interface IMainActivityListener {
    void getContact(Contact contact);
    void sendChap(Chap chap);
}
