package com.example.appmaga.Gson;

import com.example.appmaga.File.FileWork;
import com.example.appmaga.User.User;
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
            default:
                return null;
        }
    }

}
