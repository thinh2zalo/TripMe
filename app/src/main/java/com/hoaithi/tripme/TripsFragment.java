package com.hoaithi.tripme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class TripsFragment extends Fragment {

    final int ADD_TRIP_CODE = 1;

    View mRootView;
    Unbinder unbinder;

    @BindView(R.id.floating_add_button)
    FloatingActionButton addButton;

    @OnClick(R.id.floating_add_button)
    void addTrips()
    {
        Intent intent = new Intent(this.getActivity(), AddNewPlanActivity.class);
        startActivityForResult(intent, ADD_TRIP_CODE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_trips, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        initView();
        return mRootView;
    }

    void initView()
    {
        ViewPager vpTrips = mRootView.findViewById(R.id.vp_trips);
        TabLayout tlTrips = mRootView.findViewById(R.id.tl_trips);
        vpTrips.setAdapter(new TabTripsAdapter(getFragmentManager()));
        tlTrips.setupWithViewPager(vpTrips);
        vpTrips.setCurrentItem(0);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
