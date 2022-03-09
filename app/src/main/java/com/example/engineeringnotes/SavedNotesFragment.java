package com.example.engineeringnotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.engineeringnotes.adapters.ChaptersRVAdapter;
import com.example.engineeringnotes.adapters.SavedNotesRVAdapter;
import com.example.engineeringnotes.databases.SubjectNotesViewModel;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

public class SavedNotesFragment extends Fragment implements SavedNotesRVAdapter.OnItemClickListener {

    private Context context;
    private String actionBarName;
    private RecyclerView recyclerView;
    private SubjectNotesViewModel viewModel;
    private SavedNotesRVAdapter adapter;

    public SavedNotesFragment(Context context, String actionBarName) {
        this.context = context;
        this.actionBarName = actionBarName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(actionBarName);
        recyclerView = view.findViewById(R.id.saved_notes_rv);
        adapter = new SavedNotesRVAdapter(context,this);
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(SubjectNotesViewModel.class);
        viewModel.getAllChapters().observe(getViewLifecycleOwner(), chapterNames-> {
            adapter.submitList(chapterNames);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        onSlideItem().attachToRecyclerView(recyclerView);
    }

    private ItemTouchHelper onSlideItem(){
        return new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    String chapter = adapter.getChapterNameAt(viewHolder.getAbsoluteAdapterPosition());
                    onItemIconClick(chapter);
                }
            });
    }

    @Override
    public void onItemClick(String chapter) {
        String link = viewModel.getLinkFromChapter2(chapter).get(0);
        openWebPage(link);
    }

    @Override
    public void onItemIconClick(String chapter) {
        viewModel.delete(chapter);
        Toast.makeText(context, "Deleted Successfully !", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        try{
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(context, "Error !", Toast.LENGTH_SHORT).show();
        }
    }
}