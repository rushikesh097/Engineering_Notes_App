package com.example.engineeringnotes.databases;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SubjectNotesDao {

    @Query("SELECT DISTINCT subject_name FROM notes_table WHERE semester_no = :semester")
    List<String> getSubjectsFromSemester(int semester);

    @Query("SELECT chapter_name FROM notes_table WHERE subject_name = :subject")
    List<String> getChaptersFromSubject(String subject);

    @Query("SELECT link FROM notes_table WHERE chapter_name = :chapter")
    List<String> getLinkFromChapter(String chapter);

    @Query("SELECT link FROM notes_table WHERE semester_no = :semester AND subject_name = :subject")
    List<String> getLinkFromSemester(int semester,String subject);

}
