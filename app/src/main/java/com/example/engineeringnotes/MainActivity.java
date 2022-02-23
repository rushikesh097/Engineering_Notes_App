package com.example.engineeringnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = findViewById(R.id.frame_layout);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,new StartFragment(this,this))
                .commit();

        getSupportActionBar().hide();
    }
}