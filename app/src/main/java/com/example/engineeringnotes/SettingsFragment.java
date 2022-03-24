package com.example.engineeringnotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SettingsFragment extends Fragment {

    private Context context;
    private String actionBarName;
    private SwitchCompat changeTheme;
    private LinearLayout linearLayout;
    private TextView yearName;


    public SettingsFragment(Context context, String actionBarName, TextView yearName) {
        this.context = context;
        this.actionBarName = actionBarName;
        this.yearName = yearName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yearName.setText(actionBarName);
        init(view);

        linearLayout.setOnClickListener(view1 -> openWebPage());

//        changeTheme.setChecked(true);
        changeTheme.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                Toast.makeText(context, "Dark !", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Light !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(View view){
        changeTheme = view.findViewById(R.id.theme_change_switch);
        linearLayout = view.findViewById(R.id.open_info);
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void openWebPage() {
        Uri webpage = Uri.parse("https://github.com/rushikesh097/Engineering_Notes_App.git");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        try{
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(context, "Error !", Toast.LENGTH_SHORT).show();
        }
    }
}