package com.example.engineeringnotes;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity {
    //navdrawer created
    private SharedPreferences sharedPreferences;
    private LinearLayout layout;
    private TextView yearName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.options);
        yearName = findViewById(R.id.year);


        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_AUTO_BATTERY);

        layout.setOnClickListener(view -> showDialog1());
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        boolean flag = sharedPreferences.getBoolean("isFirstTime", true);
        String year = sharedPreferences.getString("year", "First Year");

        if (flag) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new StartFragment(this, this,yearName,layout))
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new HomeFragment(this, this, year,yearName,layout))
                    .commit();
        }

    }

    private void showDialog1() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.bottom_dialog_sheet);
        LinearLayout layout1 = dialog.findViewById(R.id.year_one);
        LinearLayout layout2 = dialog.findViewById(R.id.year_two);
        LinearLayout layout3 = dialog.findViewById(R.id.year_three);
        LinearLayout layout4 = dialog.findViewById(R.id.year_four);
        LinearLayout layout5 = dialog.findViewById(R.id.submit_here);
        ImageView cancel = dialog.findViewById(R.id.cancel);
        Button settings = dialog.findViewById(R.id.settings);
        Button savedNotes = dialog.findViewById(R.id.saved_notes);
        layout1.setOnClickListener(view -> {
            switchToYear(1);
            dialog.cancel();
        });

        layout2.setOnClickListener(view -> {
            switchToYear(2);
            dialog.cancel();
        });

        layout3.setOnClickListener(view -> {
            switchToYear(3);
            dialog.cancel();
        });

        layout4.setOnClickListener(view -> {
            switchToYear(4);
            dialog.cancel();
        });

        layout5.setOnClickListener(view -> {
            switchToYear(5);
            dialog.cancel();
        });

        savedNotes.setOnClickListener(view -> {
            switchToYear(6);
            dialog.cancel();
        });

        settings.setOnClickListener(view -> {
            switchToYear(7);
            dialog.cancel();
        });

        cancel.setOnClickListener(view -> dialog.cancel());
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void switchToYear(int item) {
        switch (item) {
            case 1:
                openFragment(new HomeFragment(this, this, "First Year",yearName,layout));
                Toast.makeText(MainActivity.this, "First Year Selected !", Toast.LENGTH_SHORT).show();
                break;

            case 2:
                openFragment(new HomeFragment(this, this, "Second Year",yearName,layout));
                Toast.makeText(MainActivity.this, "Second Year Selected !", Toast.LENGTH_SHORT).show();
                break;

            case 3:
                openFragment(new HomeFragment(this, this, "Third Year",yearName,layout));
                Toast.makeText(MainActivity.this, "Final Year Selected !", Toast.LENGTH_SHORT).show();

                break;

            case 4:
                openFragment(new HomeFragment(this, this, "Final Year",yearName,layout));
                Toast.makeText(MainActivity.this, "Final Year Selected !", Toast.LENGTH_SHORT).show();
                break;

            case 5:
                openWebPage();
                break;

            case 6:
                openFragment(new SavedNotesFragment(this, "Saved Notes",yearName));
                Toast.makeText(MainActivity.this, "Saved Notes", Toast.LENGTH_SHORT).show();
                break;

            case 7:
                openFragment(new SettingsFragment(this, "Settings",yearName));
                Toast.makeText(MainActivity.this, "Change Setting", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }

    private void openWebPage() {
        Uri webpage = Uri.parse("https://forms.gle/pVgZGkQonrrVdVzv8");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
        }
    }
}