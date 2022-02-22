package com.example.engineeringnotes;

import android.content.Context;
import android.os.Bundle;

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
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    VPFragmentAdapter vpFragmentAdapter;
    private Context context;
    FragmentActivity fragmentActivity;
    private String[] tabNames = new String[]{"SEM I","SEM II"};

    public HomeFragment(Context context, FragmentActivity fragmentActivity){
        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager2 = view.findViewById(R.id.viewPager2);
        tabLayout = view.findViewById(R.id.tabLayout);
        vpFragmentAdapter = new VPFragmentAdapter(fragmentActivity);
        vpFragmentAdapter.setContext(context);
        viewPager2.setAdapter(vpFragmentAdapter);

        new TabLayoutMediator(tabLayout,viewPager2,(((tab, position) -> tab.setText(tabNames[position]))))
                .attach();

        return view;
    }
}