package com.example.engineeringnotes.databases;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SubjectNotesRepository {

    private SubjectNotesDao subjectNotesDao;

    public SubjectNotesRepository(Application application){
        SubjectNotesDatabase subjectNotesDatabase = SubjectNotesDatabase.getInstance(application);
        subjectNotesDao = subjectNotesDatabase.subjectNotesDao();
    }


    public List<String> getSubjectsFromSemester(int semester){
        return subjectNotesDao.getSubjectsFromSemester(semester);
    }

    public List<String> getChaptersFromSubject(String subject){
        return subjectNotesDao.getChaptersFromSubject(subject);
    }

    public List<String> getLinkFromChapter(String chapter){
        return subjectNotesDao.getLinkFromChapter(chapter);
    }
}
