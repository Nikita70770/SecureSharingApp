package com.example.appmaga.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserHelper {
    private final String NAME_ALGORITHM = "MD5";
    private final String CHARSET_NAME = "UTF-8";

    public String getPasswordHash(String userPassword){
        MessageDigest messageDigest = null;
        StringBuffer hexString = null;
        try {
            messageDigest = MessageDigest.getInstance(NAME_ALGORITHM);
            byte[] byteData = messageDigest.digest(userPassword.getBytes(CHARSET_NAME));
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
}
