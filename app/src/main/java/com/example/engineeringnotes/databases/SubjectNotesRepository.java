package com.example.engineeringnotes.databases;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.engineeringnotes.databases.savednotes.SavedNotes;
import com.example.engineeringnotes.databases.savednotes.SavedNotesDao;
import com.example.engineeringnotes.databases.savednotes.SavedNotesDatabase;
import com.example.engineeringnotes.databases.subjectnotes.SubjectNotesDao;
import com.example.engineeringnotes.databases.subjectnotes.SubjectNotesDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubjectNotesRepository {

    private SubjectNotesDao subjectNotesDao;
    private SavedNotesDao savedNotesDao;

    public SubjectNotesRepository(Application application){
        SubjectNotesDatabase subjectNotesDatabase = SubjectNotesDatabase.getInstance(application);
        SavedNotesDatabase savedNotesDatabase = SavedNotesDatabase.getInstance(application);
        subjectNotesDao = subjectNotesDatabase.subjectNotesDao();
        savedNotesDao = savedNotesDatabase.savedNotesDao();
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

    public List<String> getLinkFromSemester(int semester,String subject){
        return  subjectNotesDao.getLinkFromSemester(semester,subject);
    }

    public void insert(SavedNotes savedNotes){
        savedNotesDao.insert(savedNotes);
    }

    public void delete(String chapter){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                savedNotesDao.delete(chapter);
            }
        });
    }

    protected LiveData<List<String>> getAllChapters(){
        return savedNotesDao.getAllChapters();
    }

    protected List<String> getLinkFromChapter2(String chapter){
        return savedNotesDao.getLinkFromChapter2(chapter);
    }

}
