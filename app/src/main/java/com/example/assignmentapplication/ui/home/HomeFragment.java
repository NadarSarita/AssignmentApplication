package com.example.assignmentapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.assignmentapplication.R;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.google.android.material.tabs.TabLayout;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    NavigationTabStrip navigationTabStrip;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(), getFragmentManager());
         viewPager = view.findViewById(R.id.view_pager);
        navigationTabStrip=view.findViewById(R.id.nts_center);
       viewPager.setAdapter(sectionsPagerAdapter);

        navigationTabStrip.setViewPager(viewPager, 1);

        return view;
    }

    }