package com.example.engineeringnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ChaptersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);
        Intent intent = getIntent();
        getSupportActionBar().setTitle(intent.getStringExtra("subject_name"));
    }
}