package com.example.engineeringnotes.databases;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SubjectNotesRepository {

    private SubjectNotesDao subjectNotesDao;
    private LiveData<List<SubjectNotes>> allSubjectNotes;

    public SubjectNotesRepository(Application application){
        SubjectNotesDatabase subjectNotesDatabase = SubjectNotesDatabase.getInstance(application);
        subjectNotesDao = subjectNotesDatabase.subjectNotesDao();
        allSubjectNotes = subjectNotesDao.getAllSubjectNotes();
    }

    public LiveData<List<SubjectNotes>> getAllSubjectNotes(){
        return allSubjectNotes;
    }

    public LiveData<List<String>> getSubjectsFromSemester(int semester){
        return subjectNotesDao.getSubjectsFromSemester(semester);
    }

    public LiveData<List<SubjectNotes>> getChaptersFromSubject(String subject){
        return subjectNotesDao.getChaptersFromSubject(subject);
    }
}
