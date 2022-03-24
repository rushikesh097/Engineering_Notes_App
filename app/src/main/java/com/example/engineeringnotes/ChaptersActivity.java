package com.example.engineeringnotes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.engineeringnotes.adapters.ChaptersRVAdapter;
import com.example.engineeringnotes.databases.savednotes.SavedNotes;
import com.example.engineeringnotes.databases.SubjectNotesViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ChaptersActivity extends AppCompatActivity implements ChaptersRVAdapter.OnChapterClickListener {

    private List<String> chapterNames;
    private RecyclerView recyclerView;
    private SubjectNotesViewModel subjectNotesViewModel;
    private String link;
    private String subject;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        Intent intent = getIntent();
        subject = intent.getStringExtra("subject_name");
//        Objects.requireNonNull(getSupportActionBar()).setTitle(subject);
        title = findViewById(R.id.action1);
        title.setText(subject);
        recyclerView = findViewById(R.id.chapters_recyclerview);

        subjectNotesViewModel = new SubjectNotesViewModel(getApplication());

        chapterNames = subjectNotesViewModel.getChaptersFromSubject(subject);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChaptersRVAdapter(chapterNames,this,subject,this));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onChapterClick(String chapter) {
        link = subjectNotesViewModel.getLinkFromChapter(chapter).get(0);
        openWebPage(link);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPopupMenuClick(String chapter, ImageView anchor) {
        link = subjectNotesViewModel.getLinkFromChapter(chapter).get(0);

        LayoutInflater inflater = (LayoutInflater) ChaptersActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.chapter_menu,null);
        LinearLayout save = view.findViewById(R.id.save_note);
        LinearLayout open = view.findViewById(R.id.open_note);
        LinearLayout share = view.findViewById(R.id.share_note);
        PopupWindow popup = new PopupWindow(view,400, LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popup.showAsDropDown(anchor,-350,0);

        save.setOnClickListener(view1 -> {
            Toast.makeText(ChaptersActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter ftf = DateTimeFormatter.ofPattern("HH:mm");
            String date = localDateTime.getDayOfMonth()+" "+localDateTime.getMonth().toString().substring(0,3)+" "+ftf.format(localDateTime);
            SavedNotes savedNotes = chapter.equals("Question Papers")? new SavedNotes(chapter+" : "+subject,link,date): new SavedNotes(chapter,link,date);
            subjectNotesViewModel.insert(savedNotes);
            popup.dismiss();
        });
        open.setOnClickListener(view12 -> {
            openWebPage(link);
            popup.dismiss();
        });
        share.setOnClickListener(view13 -> {
            shareUrlLink(link);
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
            Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
        }
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