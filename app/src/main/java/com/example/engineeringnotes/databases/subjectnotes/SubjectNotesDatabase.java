package com.example.engineeringnotes.databases.subjectnotes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.engineeringnotes.databases.savednotes.SavedNotes;

@Database(entities = {SubjectNotes.class}, version = 2)
public abstract class SubjectNotesDatabase extends RoomDatabase {

    private static SubjectNotesDatabase INSTANCE;

    public abstract SubjectNotesDao subjectNotesDao();

    public static synchronized SubjectNotesDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(), SubjectNotesDatabase.class,"NotesDB1")
                    .createFromAsset("database/NotesDB.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
