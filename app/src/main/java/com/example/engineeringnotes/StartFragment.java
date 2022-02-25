package com.example.engineeringnotes;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Objects;

public class StartFragment extends Fragment {

    private Context context;
    private FragmentActivity fragmentActivity;

    public StartFragment(Context context, FragmentActivity fragmentActivity) {
        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    RadioButton btn1,btn2,btn3,btn4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start, container, false);
        btn1 = view.findViewById(R.id.fe_btn);
        btn2 = view.findViewById(R.id.se_btn);
        btn3 = view.findViewById(R.id.te_btn);
        btn4 = view.findViewById(R.id.be_btn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeFragment((String) btn1.getText());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeFragment((String) btn2.getText());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeFragment((String) btn3.getText());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeFragment((String) btn4.getText());
            }
        });

        return view;
    }

    private void openHomeFragment(String actionBarName){
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,new HomeFragment(context,fragmentActivity, actionBarName))
                .commit();
    }
}