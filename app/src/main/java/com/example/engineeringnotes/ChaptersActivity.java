package com.example.engineeringnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.engineeringnotes.adapters.ChaptersRVAdapter;
import com.example.engineeringnotes.databases.savednotes.SavedNotes;
import com.example.engineeringnotes.databases.SubjectNotesViewModel;

import java.util.List;
import java.util.Objects;

public class ChaptersActivity extends AppCompatActivity implements ChaptersRVAdapter.OnChapterClickListener {

    private List<String> chapterNames;
    private RecyclerView recyclerView;
    private SubjectNotesViewModel subjectNotesViewModel;
    private String link;
    private String subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        Intent intent = getIntent();
        subject = intent.getStringExtra("subject_name");
        Objects.requireNonNull(getSupportActionBar()).setTitle(subject);

        recyclerView = findViewById(R.id.chapters_recyclerview);

        subjectNotesViewModel = new SubjectNotesViewModel(getApplication());

        chapterNames = subjectNotesViewModel.getChaptersFromSubject(subject);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChaptersRVAdapter(chapterNames,this,this));
        recyclerView.setHasFixedSize(true);

    }

    @Override
    public void onChapterClick(String chapter) {
        link = subjectNotesViewModel.getLinkFromChapter(chapter).get(0);
        openWebPage(link);
    }

    @Override
    public void onBookmarkClick(String chapter) {
        link = subjectNotesViewModel.getLinkFromChapter(chapter).get(0);
        SavedNotes savedNotes = chapter.equals("Question Papers")? new SavedNotes(chapter+" : "+subject,link): new SavedNotes(chapter,link);
        subjectNotesViewModel.insert(savedNotes);
    }


    @SuppressLint("QueryPermissionsNeeded")
    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        try{
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
        }
    }
}