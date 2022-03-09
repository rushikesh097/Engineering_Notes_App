package com.example.engineeringnotes.homefragments;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class SemTwoFragment extends Fragment {

    private RecyclerView recyclerView;
    private Context context;
    private String year;
    private List<String> subjects;
    private static int semester;
    private SubjectNotesViewModel viewModel;

    public SemTwoFragment(Context context,String year) {
        this.context = context;
        this.year = year;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sem_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Application application = requireActivity().getApplication();

        viewModel = new SubjectNotesViewModel(application);
        subjects = getSubjects();

        recyclerView = view.findViewById(R.id.subjects_recyclerview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new SubjectsRVAdapter(subjects,context,application,semester));
        recyclerView.setHasFixedSize(true);
    }

    private List<String> getSubjects(){
        switch (year){
            case "First Year":
                semester = 2;
                return viewModel.getSubjects(2);
            case "Second Year":
                semester = 4;
                return viewModel.getSubjects(4);
            case "Third Year":
                semester = 5;
                return viewModel.getSubjects(5);
        }
        semester = 2;
        return viewModel.getSubjects(2);
    }
}