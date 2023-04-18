package com.example.appmaga.encryption.diffie_hellman;

import com.example.appmaga.helpers.MathHelper;

public class DHAlgorithm {

    private int paramG, paramP;
    private int publicKeyUser;
    private transient int secretKey, valueKey, publicKeyContact, generalSecretKey;

    public DHAlgorithm(){
        this.paramG = MathHelper.getRandomN(1, 10);
        this.paramP = MathHelper.getRandomN(0, 10300);
    }

    public int getParamG() {
        return paramG;
    }

    public int getParamP() {
        return paramP;
    }

    public void setSecretKey() {
        this.secretKey = MathHelper.getRandomN(0, 10100);
    }

    public int getSecretKey() {
        return secretKey;
    }

    public void calcPublicKeyUser(){
        publicKeyUser = MathHelper.calcPublicKey(
                getParamG(),
                getSecretKey(),
                getParamP()
        );
    }

    public int getPublicKeyUser() {
        return publicKeyUser;
    }

    public void setPublicKeyContact(int publicKeyContact) {
        this.publicKeyContact = publicKeyContact;
    }

    public int getPublicKeyContact() {
        return publicKeyContact;
    }

    public int getValueKey() {
        return valueKey;
    }

    public void setValueKey(int valueKey) {
        if(valueKey == -1){ this.valueKey = getPublicKeyContact(); }
        else { this.valueKey = valueKey; }
    }

    public void calcGeneralSecretKey(){
        generalSecretKey = MathHelper.calcGeneralSecretKey(
                getValueKey(),
                getSecretKey(),
                getParamP()
        );
    }

    public int getGeneralSecretKey() {
        return generalSecretKey;
    }
}
