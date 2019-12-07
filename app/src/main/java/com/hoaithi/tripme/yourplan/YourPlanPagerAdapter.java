package com.hoaithi.tripme.yourplan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hoaithi.tripme.OngoingTripsFragment;
import com.hoaithi.tripme.PastTripsFragment;

import java.util.ArrayList;

public class YourPlanPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mData = new ArrayList<>();
    public static Integer position;

    public YourPlanPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mData.add(new YourPlanFragment());
        mData.add(new YourCostFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        System.out.println("page "+position);
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                this.position = 0;
                return "Lịch trình";
            case 1:
                this.position = 1;
                return "Chi phí";
            default:
                this.position = 0;
                return "";
        }
    }
}
