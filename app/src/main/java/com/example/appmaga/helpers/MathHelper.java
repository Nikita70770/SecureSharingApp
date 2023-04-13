package com.example.appmaga.helpers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MathHelper {

    private static final String NAME_ALGORITHM = "MD5";
    private static final String CHARSET_NAME = "UTF-8";

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
}
