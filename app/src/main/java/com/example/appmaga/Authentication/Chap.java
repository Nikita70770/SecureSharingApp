package com.example.appmaga.Authentication;

import com.example.appmaga.User.User;

public class Chap {
    private String userPassword, randUserN;
    private String loginContact, passwordContact, randValContact;

    private String resValueUser, resValueContact;

    public Chap(User user, String loginContact, String passwordContact, String randValContact){
        this.userPassword = user.getPassword();
        this.randUserN = user.getRandN();
        this.loginContact = loginContact;
        this.passwordContact = passwordContact;
        this.randValContact = randValContact;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getRandUserN() {
        return randUserN;
    }

    public String getLoginContact() {
        return loginContact;
    }

    public String getPasswordContact() {
        return passwordContact;
    }

    public String getRandValContact() {
        return randValContact;
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
}
