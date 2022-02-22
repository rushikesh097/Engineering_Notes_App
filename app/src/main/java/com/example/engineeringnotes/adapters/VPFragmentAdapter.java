package com.example.engineeringnotes.adapters;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.engineeringnotes.homefragments.SemOneFragment;
import com.example.engineeringnotes.homefragments.SemTwoFragment;

public class VPFragmentAdapter extends FragmentStateAdapter {

    private String[] tabNames = new String[]{"SEM I","SEM II"};

    private Context context;
    public VPFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SemOneFragment(context);
            case 1:
                return new SemTwoFragment();
        }
        return new SemOneFragment(context);
    }

    @Override
    public int getItemCount() {
        return tabNames.length;
    }

    public void setContext(Context context){
        this.context = context;
    }
}
