package com.example.appmaga.Gson;

import com.example.appmaga.Authentication.Chap;
import com.example.appmaga.model.entities.Contact;
import com.example.appmaga.File.FileWork;
import com.example.appmaga.model.entities.User;
import com.google.gson.Gson;

public class GsonWork {

    private static final Gson gson = new Gson();

    public static String performSerialization(Object object){
        return gson.toJson(object);
    }

    public static Object performDeserialization(String data, String className){
        switch (className){
            case "User":
                return gson.fromJson(data, User.class);
            case "FileWork":
                return gson.fromJson(data, FileWork.class);
            case "Contact":
                return gson.fromJson(data, Contact.class);
            case "Chap":
                return gson.fromJson(data, Chap.class);
            default:
                return null;
        }
    }

}
