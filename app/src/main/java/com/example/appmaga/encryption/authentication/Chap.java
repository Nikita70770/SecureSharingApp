package com.example.appmaga.encryption.authentication;

import android.content.Context;

import com.example.appmaga.model.entities.User;
import com.example.appmaga.model.preferences.PreferencesStorage;
import com.example.appmaga.helpers.MathHelper;

public class Chap {
    private User user;
    private String userPassword, randUserN;
    private String loginContact, passwordContact, randValContact;

    private String calcHashSum;
    private String resValueUser, resValueContact;

    public Chap(Context context, String loginContact, String passwordContact, String randValContact){
        this.user = PreferencesStorage.init(context).getUser();
        this.userPassword = user.getPassword();
        this.randUserN = user.getRandValue();
        this.loginContact = loginContact;
        this.passwordContact = passwordContact;
        this.randValContact = randValContact;
    }

    public boolean makeChapAuthoWithContact(int numStep){
        String hashSum;
        switch (numStep){
            case 1:
                hashSum = MathHelper.getHash(getUserPassword() + getRandValContact());
                setCalcHashSum(hashSum);
                return true;
            case 2:
                setResValueUser(getCalcHashSum());
                hashSum = MathHelper.getHash(getPasswordContact() + getRandUserN());
                setResValueContact(hashSum);
                return true;
            case 3:
                return getResValueUser().equals(getResValueContact()) ? true : false;
            default:
                return false;
        }
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
}
