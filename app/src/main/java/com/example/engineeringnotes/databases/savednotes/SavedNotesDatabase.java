package com.example.engineeringnotes.databases.savednotes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {SavedNotes.class}, version = 1)
abstract public class SavedNotesDatabase extends RoomDatabase {
    private static SavedNotesDatabase INSTANCE;

    public abstract SavedNotesDao savedNotesDao();

    public static synchronized SavedNotesDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SavedNotesDatabase.class,"SavedNotesDB1")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
