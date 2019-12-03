package com.hoaithi.tripme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hoaithi.tripme.OngoingTripsFragment;
import com.hoaithi.tripme.PastTripsFragment;

public class TabTripsAdapter extends FragmentPagerAdapter {

    private String tabNames[] = {"Sắp tới", "Đã qua"};
    private OngoingTripsFragment ongoingTripsFragment;
    private PastTripsFragment pastTripsFragment;

    public TabTripsAdapter(@NonNull FragmentManager fm) {
        super(fm);
        ongoingTripsFragment = new OngoingTripsFragment();
        pastTripsFragment = new PastTripsFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return ongoingTripsFragment;
        else
            return pastTripsFragment;
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }
}
