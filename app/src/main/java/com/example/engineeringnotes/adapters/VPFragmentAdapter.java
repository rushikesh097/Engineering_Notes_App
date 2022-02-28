package com.example.engineeringnotes.adapters;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.engineeringnotes.homefragments.SemOneFragment;
import com.example.engineeringnotes.homefragments.SemTwoFragment;

public class VPFragmentAdapter extends FragmentStateAdapter {

    private final String[] tabNames = new String[]{"SEM I","SEM II"};
    private String year;

    private Context context;
    public VPFragmentAdapter(@NonNull FragmentActivity fragmentActivity,Context context,String year) {
        super(fragmentActivity);
        this.context = context;
        this.year = year;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SemOneFragment(context,year);
            case 1:
                return new SemTwoFragment(context,year);
        }
        return new SemOneFragment(context,year);
    }

    @Override
    public int getItemCount() {
        return tabNames.length;
    }

}
