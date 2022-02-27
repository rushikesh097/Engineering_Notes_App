package com.example.engineeringnotes.databases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class SubjectNotes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String chapter_name;

    private String link;

    private String subject_name;

    private int semester_no;

}
