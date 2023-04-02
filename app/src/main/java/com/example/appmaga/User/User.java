package com.example.appmaga.User;

public class User {
    private String login, password, randN;

    public User(String login, String password, String value){
        this.login = login;
        this.password = password;
        this.randN = value;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRandN() {
        return randN;
    }
}
