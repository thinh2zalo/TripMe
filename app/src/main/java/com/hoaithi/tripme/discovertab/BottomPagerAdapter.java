package com.hoaithi.tripme.discovertab;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hoaithi.tripme.DiscoveryFragment;
import com.hoaithi.tripme.R;
import com.hoaithi.tripme.TripsFragment;
import com.hoaithi.tripme.ui.empty.EmptyFragment;

import java.util.ArrayList;

public class BottomPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public BottomPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
        initData();
    }

    private ArrayList<Fragment> mData = new ArrayList<>();

    private void initData() {
        mData.add(DiscoveryFragment.newInstance());
        mData.add(new TripsFragment());
        mData.add(EmptyFragment.newInstance());
        mData.add(EmptyFragment.newInstance());
        mData.add(EmptyFragment.newInstance());
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return mData.size();
    }

    // Returns the fragment to display for that page
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.decovery);
            case 1:
                return mContext.getResources().getString(R.string.my_schedule);
            case 2:
                return mContext.getResources().getString(R.string.save);
            case 3:
                return mContext.getResources().getString(R.string.notification);
            case 4:
                return mContext.getResources().getString(R.string.other);
            default:
                return mContext.getResources().getString(R.string.decovery);
        }
    }
}