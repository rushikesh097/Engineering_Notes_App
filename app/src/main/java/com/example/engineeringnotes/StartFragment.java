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
        btn1 = view.findViewById(R.id.radioButton);
        btn2 = view.findViewById(R.id.radioButton2);
        btn3 = view.findViewById(R.id.radioButton3);
        btn4 = view.findViewById(R.id.radioButton4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Zal", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout,new HomeFragment(context,fragmentActivity))
                        .commit();
            }
        });
        return view;
    }
}