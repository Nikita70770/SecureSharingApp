package com.example.appmaga.model.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String login;

    private String password;

    private String randValue;

    public Contact(String login, String password, String randN){
        this.login = login;
        this.password = password;
        this.randValue = randN;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
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
