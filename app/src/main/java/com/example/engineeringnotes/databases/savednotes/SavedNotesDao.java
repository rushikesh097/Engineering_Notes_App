package com.example.engineeringnotes.databases.savednotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedNotesDao {
    @Insert
    void insert(SavedNotes savedNotes);

    @Query("DELETE FROM saved_notes_table WHERE chapterName = :chapter")
    void delete(String chapter);

    @Query("SELECT DISTINCT chapterName FROM saved_notes_table")
    LiveData<List<String>> getAllChapters();

    @Query("SELECT DISTINCT link FROM saved_notes_table WHERE chapterName = :chapter")
    List<String> getLinkFromChapter2(String chapter);
}
