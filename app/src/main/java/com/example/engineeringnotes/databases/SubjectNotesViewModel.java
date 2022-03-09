package com.example.engineeringnotes.databases;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.engineeringnotes.databases.savednotes.SavedNotes;

import java.util.List;

public class SubjectNotesViewModel extends AndroidViewModel {
    private SubjectNotesRepository repository;

    public SubjectNotesViewModel(@NonNull Application application) {
        super(application);
        repository = new SubjectNotesRepository(application);
    }


    public List<String> getSubjects(int semester){
        return repository.getSubjectsFromSemester(semester);
    }

    public List<String> getChaptersFromSubject(String subject){
        return repository.getChaptersFromSubject(subject);
    }

    public List<String> getLinkFromChapter(String chapter){
        return repository.getLinkFromChapter(chapter);
    }

    public List<String> getLinkFromSemester(int semester,String subject){
        return  repository.getLinkFromSemester(semester,subject);
    }

    public void insert(SavedNotes savedNotes){
        repository.insert(savedNotes);
    }

    public void delete(String chapter){
        repository.delete(chapter);
    }

    public LiveData<List<String>> getAllChapters(){
        return repository.getAllChapters();
    }

    public List<String> getLinkFromChapter2(String chapter){
        return repository.getLinkFromChapter2(chapter);
    }
}
