package com.example.appmaga.Contact;

public class Contact {
    private String login, password, randValue;

    public Contact(String login, String password, String randN){
        this.login = login;
        this.password = password;
        this.randValue = randN;
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
