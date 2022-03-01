package com.example.engineeringnotes.homefragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.engineeringnotes.R;
import com.example.engineeringnotes.adapters.SubjectsRVAdapter;
import com.example.engineeringnotes.databases.SubjectNotesViewModel;

import java.util.List;

public class SemOneFragment extends Fragment {

    private RecyclerView recyclerView;


    private Context context;
    private String year;
    private List<String> subjects;

    public SemOneFragment(Context context, String year) {
        this.context = context;
        this.year = year;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sem_one, container, false);

        SubjectNotesViewModel notesViewModel = new SubjectNotesViewModel(requireActivity().getApplication());
        subjects = getSubjects(notesViewModel);

        recyclerView = view.findViewById(R.id.subjects_recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new SubjectsRVAdapter(subjects,context));
        recyclerView.setHasFixedSize(true);
        return view;
    }

    private List<String> getSubjects(SubjectNotesViewModel subjectNotesViewModel){
        switch (year){
            case "First Year":
                return subjectNotesViewModel.getSubjects(1);
            case "Second Year":
                return subjectNotesViewModel.getSubjects(3);
            case "Third Year":
                return subjectNotesViewModel.getSubjects(5);
        }
        return subjectNotesViewModel.getSubjects(1);
    }
}