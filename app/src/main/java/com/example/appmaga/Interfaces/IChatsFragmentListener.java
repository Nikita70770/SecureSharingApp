package com.example.appmaga.Interfaces;

import com.example.appmaga.Authentication.Chap;
import com.example.appmaga.Contact.Contact;

public interface IChatsFragmentListener {
    void sendContact(Contact contact);
    void setChap(Chap chap);
}
