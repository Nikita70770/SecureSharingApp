package com.example.appmaga.User;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class UserSettings {
    public static final String APP_PREFERENCES = "UserSettings";
    public static final String APP_PREFERENCES_USER_OBJECT = "UserObject";
    public static final int ACCESS_MODE = Context.MODE_PRIVATE;
    private static final Gson gson = new Gson();

    public static final String APP_PREFERENCES_LOGIN_NAME = "LoginName";
    public static final String APP_PREFERENCES_PASSWORD = "Password";

    public static void saveUser(SharedPreferences.Editor editorSettings, User user){
        String jsonUser = gson.toJson(user);
        editorSettings.putString(APP_PREFERENCES_USER_OBJECT, jsonUser);
        editorSettings.apply();
    }

    public static User getUser(SharedPreferences settings){
        String jsonUser = settings.getString(APP_PREFERENCES_USER_OBJECT, null);
        User user = gson.fromJson(jsonUser, User.class);
        return user != null ? user : null;
    }
}
