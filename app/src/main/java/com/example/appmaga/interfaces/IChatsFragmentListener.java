package com.example.appmaga.interfaces;

import com.example.appmaga.Authentication.Chap;
import com.example.appmaga.model.entities.Contact;

public interface IChatsFragmentListener {
    void sendContact(Contact contact);
    void setChap(Chap chap);
}
