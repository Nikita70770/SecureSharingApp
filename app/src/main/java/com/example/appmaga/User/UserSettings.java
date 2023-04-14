package com.example.appmaga.User;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appmaga.Gson.GsonWork;
import com.example.appmaga.model.entities.User;

public class UserSettings {
    public static final String APP_PREFERENCES = "UserSettings";
    public static final String APP_PREFERENCES_USER_OBJECT = "UserObject";
    public static final int ACCESS_MODE = Context.MODE_PRIVATE;

    public static User user;

    public static void saveUser(SharedPreferences.Editor editorSettings, User user){
        String jsonUser = GsonWork.performSerialization(user);
        editorSettings.putString(APP_PREFERENCES_USER_OBJECT, jsonUser);
        editorSettings.apply();
    }

    public static void requestUser(SharedPreferences settings){
        String jsonUser = settings.getString(APP_PREFERENCES_USER_OBJECT, null);
        setUser((User) GsonWork.performDeserialization(jsonUser, User.class.getSimpleName()));
    }

    public static User getUser() {
        return user != null ? user : null;
    }

    public static void setUser(User user) {
        UserSettings.user = user;
    }
}
