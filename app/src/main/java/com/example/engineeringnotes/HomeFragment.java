package com.example.engineeringnotes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.engineeringnotes.adapters.VPFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class HomeFragment extends Fragment {
    //22-02-22
    //hbdc
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private VPFragmentAdapter vpFragmentAdapter;
    private Context context;
    private FragmentActivity fragmentActivity;
    private final String[] tabNames = new String[]{"SEM I","SEM II"};
    private String actionBarName;

    public HomeFragment(Context context, FragmentActivity fragmentActivity, String actionBarName){
        this.context = context;
        this.fragmentActivity = fragmentActivity;
        this.actionBarName = actionBarName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().setTitle(actionBarName);

        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirstTime",false);
        editor.putString("year",actionBarName);
        editor.apply();

        viewPager2 = view.findViewById(R.id.viewPager2);
        tabLayout = view.findViewById(R.id.tabLayout);
        vpFragmentAdapter = new VPFragmentAdapter(fragmentActivity,context,actionBarName);
        viewPager2.setAdapter(vpFragmentAdapter);

        new TabLayoutMediator(tabLayout,viewPager2,(((tab, position) -> tab.setText(tabNames[position]))))
                .attach();

    }
}