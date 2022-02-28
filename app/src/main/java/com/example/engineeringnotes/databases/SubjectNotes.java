package com.example.engineeringnotes.databases;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "notes_table")
public class SubjectNotes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NotNull
    private String chapter_name;

    @NotNull
    private String link;

    @NotNull
    private String subject_name;

    @NotNull
    private int semester_no;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getSemester_no() {
        return semester_no;
    }

    public void setSemester_no(int semester_no) {
        this.semester_no = semester_no;
    }

}
