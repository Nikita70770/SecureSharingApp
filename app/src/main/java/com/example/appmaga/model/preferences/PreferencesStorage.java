package com.example.appmaga.model.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appmaga.helpers.GsonWork;
import com.example.appmaga.model.entities.User;

public class PreferencesStorage {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private static final String APP_PREFERENCES = "UserStorage";
    private static final String APP_PREFERENCES_USER_OBJECT = "UserObject";
    private static final String APP_PREFERENCES_KEY_VALUE = "GeneralKey";
    private static final int ACCESS_MODE_PRIVATE = Context.MODE_PRIVATE;

    public static PreferencesStorage init(Context context){
        preferences = context.getSharedPreferences(APP_PREFERENCES, ACCESS_MODE_PRIVATE);
        editor = preferences.edit();
        return new PreferencesStorage();
    }

    public void saveUser(User user) {
        String jsonUser = GsonWork.performSerialization(user);
        editor.putString(APP_PREFERENCES_USER_OBJECT, jsonUser);
        editor.apply();
    }

    public void saveGeneralSecretKey(int key){
        if(getGeneralSecretKey() != -1){ preferences.edit().remove(APP_PREFERENCES_KEY_VALUE).commit(); }
        editor.putInt(APP_PREFERENCES_KEY_VALUE, key);
        editor.apply();
    }

    public int getGeneralSecretKey(){
        return preferences.getInt(APP_PREFERENCES_KEY_VALUE, -1);
    }

    public User getUser() {
        String jsonUser = preferences.getString(APP_PREFERENCES_USER_OBJECT, null);
        return (User) GsonWork.performDeserialization(jsonUser, User.class.getSimpleName());
    }
}
