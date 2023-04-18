package com.example.appmaga.encryption.diffie_hellman;

import com.example.appmaga.helpers.MathHelper;

public class DHAlgorithm {

    private int paramG, paramP;
    private int publicKeyUser, publicKeyContact;
    private transient int secretKey, generalSecretKey;

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
        publicKeyUser = MathHelper.calcPublicKey(getParamG(), getSecretKey(), getParamP());
    }

    public int getPublicKeyUser() {
        return publicKeyUser;
    }

    public int getPublicKeyContact() {
        return publicKeyContact;
    }

    public void setPublicKeyContact(int publicKeyContact) {
        this.publicKeyContact = publicKeyContact;
    }

    public void calcGeneralSecretKey(){
        generalSecretKey = MathHelper.calcGeneralSecretKey(
                getPublicKeyContact(),
                getSecretKey(),
                getParamP()
        );
    }

    public int getGeneralSecretKey() {
        return generalSecretKey;
    }
}
