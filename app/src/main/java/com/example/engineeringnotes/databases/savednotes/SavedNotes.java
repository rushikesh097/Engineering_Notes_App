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

    public SavedNotes(@NotNull String chapterName, @NotNull String link) {
        this.chapterName = chapterName;
        this.link = link;
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

}
