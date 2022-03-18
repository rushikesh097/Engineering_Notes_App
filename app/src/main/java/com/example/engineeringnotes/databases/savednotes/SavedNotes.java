package com.example.engineeringnotes.databases.savednotes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "saved_notes_table")
public class SavedNotes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String chapterName;

    private String link;

    private String date;

    public SavedNotes(@NotNull String chapterName, @NotNull String link, String date) {
        this.chapterName = chapterName;
        this.link = link;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(@NotNull String chapterName) {
        this.chapterName = chapterName;
    }

    public int getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(@NotNull String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
