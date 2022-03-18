package com.example.engineeringnotes.databases.savednotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedNotesDao {
    @Insert
    void insert(SavedNotes savedNotes);

    @Delete
    void delete(SavedNotes savedNotes);

    @Query("SELECT chapterName FROM saved_notes_table WHERE chapterName = :chapter_name")
    List<String> isChapterPresent(String chapter_name);

    @Query("SELECT * FROM saved_notes_table")
    LiveData<List<SavedNotes>> getAllSavedNotes();
}
