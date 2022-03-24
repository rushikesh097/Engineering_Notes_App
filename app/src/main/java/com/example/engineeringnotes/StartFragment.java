package com.example.engineeringnotes;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;


public class StartFragment extends Fragment {

    private Context context;
    private FragmentActivity fragmentActivity;
    private TextView yearName;
    private LinearLayout layout;

    public StartFragment(Context context, FragmentActivity fragmentActivity, TextView yearName, LinearLayout layout) {
        this.context = context;
        this.fragmentActivity = fragmentActivity;
        this.yearName = yearName;
        this.layout = layout;
    }

    RadioButton btn1,btn2,btn3,btn4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout.setVisibility(View.INVISIBLE);

        init(view);

        btn1.setOnClickListener(view1 -> openHomeFragment((String) btn1.getText()));

        btn2.setOnClickListener(view12 -> openHomeFragment((String) btn2.getText()));

        btn3.setOnClickListener(view13 -> openHomeFragment((String) btn3.getText()));

        btn4.setOnClickListener(view14 -> openHomeFragment((String) btn4.getText()));
    }

    private void init(View view){
        btn1 = view.findViewById(R.id.fe_btn);
        btn2 = view.findViewById(R.id.se_btn);
        btn3 = view.findViewById(R.id.te_btn);
        btn4 = view.findViewById(R.id.be_btn);
    }

    private void openHomeFragment(String actionBarName){
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,new HomeFragment(context,fragmentActivity, actionBarName, yearName, layout))
                .commit();
    }
}



