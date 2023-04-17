package com.example.appmaga.encryption.diffie_hellman;

import android.util.Log;

import com.example.appmaga.helpers.MathHelper;

import java.math.BigInteger;

public class DHAlgorithm {

    private int paramG, paramP;
    private int publicKeyContact;
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

    public BigInteger calcPublicKeyUser(){
        return MathHelper.calcPublicKey(getParamG(), getSecretKey(), getParamP());
    }

    public int getPublicKeyContact() {
        return publicKeyContact;
    }

    public int getGeneralSecretKey() {
        return generalSecretKey;
    }
}
