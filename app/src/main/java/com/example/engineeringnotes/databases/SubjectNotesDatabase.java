package com.example.engineeringnotes.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.engineeringnotes.StartFragment;

@Database(entities = {SubjectNotes.class}, version = 2)
public abstract class SubjectNotesDatabase extends RoomDatabase {

    private static SubjectNotesDatabase INSTANCE;

    public abstract SubjectNotesDao subjectNotesDao();

    public static synchronized SubjectNotesDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SubjectNotesDatabase.class,"NotesDB1")
                    .createFromAsset("database/NotesDB.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
            SupportSQLiteDatabase supportSQLiteOpenHelper = INSTANCE.getOpenHelper().getWritableDatabase();
        }
        return INSTANCE;
    }

}
