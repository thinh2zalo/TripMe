package com.hoaithi.tripme;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class TripsFragment extends Fragment {

    View mRootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_trips, container, false);
        initView();
        return mRootView;
    }

    void initView()
    {
        ViewPager vpTrips = mRootView.findViewById(R.id.vp_trips);
        TabLayout tlTrips = mRootView.findViewById(R.id.tl_trips);
        vpTrips.setAdapter(new TabTripsAdapter(getFragmentManager()));
        tlTrips.setupWithViewPager(vpTrips);
    }
}
