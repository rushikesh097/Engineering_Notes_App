package com.example.engineeringnotes.databases;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SubjectNotesViewModel extends AndroidViewModel {
    private SubjectNotesRepository repository;
    private LiveData<List<SubjectNotes>> allSubjectNotes;

    public SubjectNotesViewModel(@NonNull Application application) {
        super(application);
        repository = new SubjectNotesRepository(application);
        allSubjectNotes = repository.getAllSubjectNotes();
    }

    public LiveData<List<SubjectNotes>> getAllSubjectNotes(){
        return allSubjectNotes;
    }

    public LiveData<List<String>> getSubjects(int semester){
        return repository.getSubjectsFromSemester(semester);
    }

    public LiveData<List<SubjectNotes>> getChaptersFromSubject(String subject){
        return repository.getChaptersFromSubject(subject);
    }
}
