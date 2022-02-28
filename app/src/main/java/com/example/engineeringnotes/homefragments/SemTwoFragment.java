package com.example.engineeringnotes.homefragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.engineeringnotes.R;
import com.example.engineeringnotes.databases.SubjectNotesViewModel;

import java.util.List;

public class SemTwoFragment extends Fragment {

    private Context context;
    private String year;
    private List<String> sub;
    public SemTwoFragment(Context context,String year) {
        this.context = context;
        this.year = year;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sem_two, container, false);

        ListView listView = view.findViewById(R.id.list_view2);

        sub = getSubjects(new SubjectNotesViewModel(requireActivity().getApplication()));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sub);
        listView.setAdapter(arrayAdapter);

        return view;
    }

    private List<String> getSubjects(SubjectNotesViewModel subjectNotesViewModel){
        switch (year){
            case "First Year":
                return subjectNotesViewModel.getSubjects(2);
            case "Second Year":
                return subjectNotesViewModel.getSubjects(4);
            case "Third Year":
                return subjectNotesViewModel.getSubjects(5);
        }
        return subjectNotesViewModel.getSubjects(2);
    }
}