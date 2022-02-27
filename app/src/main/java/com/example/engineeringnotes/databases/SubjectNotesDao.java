package com.example.engineeringnotes.databases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SubjectNotesDao {

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    LiveData<List<SubjectNotes>> getAllSubjectNotes();

    @Query("SELECT DISTINCT subject_name FROM notes_table WHERE semester_no = :semester")
    LiveData<List<String>> getSubjectsFromSemester(int semester);

    @Query("SELECT * FROM notes_table WHERE subject_name = :subject")
    LiveData<List<SubjectNotes>> getChaptersFromSubject(String subject);

}
