package com.example.appmaga.model.entities;

import com.example.appmaga.helpers.MathHelper;

public class User {
    private String login, password, randValue;

    public User(String login, String password){
        this.login = login;
        this.password = MathHelper.getHash(password);
        this.randValue = String.valueOf(MathHelper.getRandomN(0, 1000000));
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRandValue() {
        return randValue;
    }
}
