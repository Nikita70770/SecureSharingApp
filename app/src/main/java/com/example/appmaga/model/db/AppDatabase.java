package com.example.appmaga.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appmaga.model.dao.ContactDAO;
import com.example.appmaga.model.entities.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContactDAO contactDAO();
    public static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "AppDatabase";

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,
                            AppDatabase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }
}
