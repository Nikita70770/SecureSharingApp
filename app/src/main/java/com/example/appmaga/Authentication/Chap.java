package com.example.appmaga.Authentication;

import android.content.Context;

import com.example.appmaga.User.User;
import com.example.appmaga.User.UserSettings;

public class Chap {
    private User user = UserSettings.getUser();
    private String userPassword, randUserN;
    private String loginContact, passwordContact, randValContact;

    private String calcHashSum;
    private String resValueUser, resValueContact;

    public Chap(String loginContact, String passwordContact, String randValContact){
        this.userPassword = user.getPassword();
        this.randUserN = user.getRandN();
        this.loginContact = loginContact;
        this.passwordContact = passwordContact;
        this.randValContact = randValContact;
    }

    public boolean makeChapAuthoWithContact(int numStep){
        String hashSum;
        switch (numStep){
            case 1:
                hashSum = ChapHelper.getHash(getUserPassword() + getRandValContact());
                setCalcHashSum(hashSum);
                return true;
            case 2:
                setResValueUser(getCalcHashSum());
                hashSum = ChapHelper.getHash(getPasswordContact() + getRandUserN());
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
