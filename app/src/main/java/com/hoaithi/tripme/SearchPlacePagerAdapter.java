package com.hoaithi.tripme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class SearchPlacePagerAdapter extends FragmentPagerAdapter {
    String tabNames[] = {"Tham quan", "Nhà hàng", "Khách sạn"};

    Fragment thamquanFragment, nhahangFragment, khachsanFragment;

    public SearchPlacePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        thamquanFragment = new ThamquanFragment();
        nhahangFragment = new ThamquanFragment();
        khachsanFragment = new ThamquanFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0)
                return thamquanFragment;
        if (position == 1)
                return  nhahangFragment;
        if (position == 2)
                return  khachsanFragment;
        return null;
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
