package com.example.appmaga.encryption.authentication;

import android.content.Context;

import com.example.appmaga.model.entities.Contact;
import com.example.appmaga.model.entities.User;
import com.example.appmaga.model.preferences.PreferencesStorage;
import com.example.appmaga.helpers.MathHelper;

public class Chap {
    private User user;
    private Contact contact;
    private String calcHashSum;
    private String resValueUser, resValueContact;
    private boolean resultAuthentication;

    public Chap(User user, Contact contact){
        this.user = user;
        this.contact = contact;
    }

    public void makeChapAuthoWithContact(int numStep){
        String hashSum;
        switch (numStep){
            case 1:
                hashSum = MathHelper.getHash(user.getPassword() + contact.getRandValue());
                setCalcHashSum(hashSum);
                break;
            case 2:
                setResValueUser(getCalcHashSum());
                hashSum = MathHelper.getHash(contact.getPassword() + user.getRandValue());
                setResValueContact(hashSum);
                break;
            case 3:
                boolean result = getResValueUser().equals(getResValueContact()) ? true : false;
                setResultAuthentication(result);
        }
    }

    public Contact getContact() {
        return contact;
    }

    public String getCalcHashSum() {
        return calcHashSum;
    }

    public void setCalcHashSum(String calcHashSum) {
        this.calcHashSum = calcHashSum;
    }

    public String getResValueUser() {
        return resValueUser;
    }

    public void setResValueUser(String resValueUser) {
        this.resValueUser = resValueUser;
    }

    public String getResValueContact() {
        return resValueContact;
    }

    public void setResValueContact(String resValueContact) {
        this.resValueContact = resValueContact;
    }

    public void setResultAuthentication(boolean resultAuthentication) {
        this.resultAuthentication = resultAuthentication;
    }

    public boolean isResultAuthentication() {
        return resultAuthentication;
    }
}
