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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.engineeringnotes.adapters.SavedNotesRVAdapter;
import com.example.engineeringnotes.databases.SubjectNotesViewModel;
import com.example.engineeringnotes.databases.savednotes.SavedNotes;

public class SavedNotesFragment extends Fragment implements SavedNotesRVAdapter.OnItemClickListener {

    private Context context;
    private String actionBarName;
    private RecyclerView recyclerView;
    private SubjectNotesViewModel viewModel;
    private SavedNotesRVAdapter adapter;
    private TextView yearName;

    public SavedNotesFragment(Context context, String actionBarName, TextView yearName) {
        this.context = context;
        this.actionBarName = actionBarName;
        this.yearName = yearName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_saved_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        yearName.setText(actionBarName);

        recyclerView = view.findViewById(R.id.saved_notes_rv);
        adapter = new SavedNotesRVAdapter(context,this);
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(SubjectNotesViewModel.class);
        viewModel.getAllSavedNotes().observe(getViewLifecycleOwner(), savedNotes-> adapter.submitList(savedNotes));
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
                    SavedNotes note= adapter.getNoteAt(viewHolder.getAbsoluteAdapterPosition());
                    deleteSavedNote(note);
                }
            });
    }

    @Override
    public void onItemClick(String link) {
        openWebPage(link);
    }


    @Override
    public void onItemIconClick(SavedNotes note, ImageView anchor) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.saved_notes_menu, null);
        LinearLayout delete = view.findViewById(R.id.delete_note);
        LinearLayout open = view.findViewById(R.id.open_note);
        LinearLayout share = view.findViewById(R.id.share_note);
        PopupWindow popup = new PopupWindow(view, 400, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popup.showAsDropDown(anchor, -350, 0);

        delete.setOnClickListener(view1 -> {
            deleteSavedNote(note);
            Toast.makeText(context, "Deleted Successfully !", Toast.LENGTH_SHORT).show();
            popup.dismiss();
        });

        open.setOnClickListener(view12 -> {
            onItemClick(note.getLink());
            popup.dismiss();
        });

        share.setOnClickListener(view13 -> {
            shareUrlLink(note.getLink());
            popup.dismiss();
        });
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

    private void deleteSavedNote(SavedNotes note)
    {
        viewModel.delete(note);
        Toast.makeText(context, "Deleted Successfully !", Toast.LENGTH_SHORT).show();
    }

    private void shareUrlLink(String link)
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Engineering Notes App\n"+link);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "Share Notes");
        startActivity(shareIntent);
    }
}