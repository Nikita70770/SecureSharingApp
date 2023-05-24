package com.example.appmaga.helpers;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MathHelper {

    private static final String NAME_ALGORITHM = "MD5";
    private static final String CHARSET_NAME = "UTF-8";
    private static final int MAX_SEQUENCE_LENGTH = 64;

    public static String getHash(String value){
        MessageDigest messageDigest = null;
        StringBuffer hexString = null;
        try {
            messageDigest = MessageDigest.getInstance(NAME_ALGORITHM);
            byte[] byteData = messageDigest.digest(value.getBytes(CHARSET_NAME));
            hexString = new StringBuffer();

            for(byte elem : byteData){
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return hexString.toString();
    }


    public static int getRandomN(int min, int max){
        int randValue = (int) (Math.random() * ((max - min) + 1) + min);
        return randValue;
    }


    private static BigInteger pow(int value, int powValue){
        BigInteger val = new BigInteger(String.valueOf(value));
        BigInteger resPow = val.pow(powValue);
        return resPow;
    }
    public static int calcPublicKey(int valG, int secretKey, int valP){
        BigInteger resPow = pow(valG, secretKey);
        BigInteger publicKey = resPow.mod(BigInteger.valueOf(valP));
        return publicKey.intValue();
    }
    public static int calcGeneralSecretKey(int publicKeyContact, int secretKey, int valP){
        BigInteger resPow = pow(publicKeyContact, secretKey);
        BigInteger generalSecretKey = resPow.mod(BigInteger.valueOf(valP));
        return generalSecretKey.intValue();
    }


    public static int getNumFromBinSequence(String value){
        return Integer.parseInt(value, 2);
    }
    public static String getLargeBinSequence(long[] data){
        String binNum, binSequence = "";
        int lenBinNum;
        for(long num : data){
            binNum = Long.toBinaryString(num);
            lenBinNum = binNum.length();
            if(lenBinNum < MAX_SEQUENCE_LENGTH){
                for(int i = lenBinNum; i < MAX_SEQUENCE_LENGTH; i++) {
                    binNum = "0" + binNum;
                }
            }
            binSequence += binNum;
        }
        return binSequence;
    }
    public String calcSumByModTwo(String str1, String str2){
        String sum = "";
        char[] sequence1 = str1.toCharArray();
        char[] sequence2 = str2.toCharArray();
        int maxLen = str1.length() > str2.length() ? str1.length() : str2.length();

        for(int i = 0; i < maxLen; i++){
            int val = (sequence1[i] ^ sequence2[i]);
            sum += String.valueOf(val);
        }
        return sum;
    }
}
