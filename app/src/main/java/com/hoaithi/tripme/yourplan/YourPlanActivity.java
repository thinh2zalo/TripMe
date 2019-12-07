package com.hoaithi.tripme.yourplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.hoaithi.tripme.AddCost;
import com.hoaithi.tripme.AddNewPlanActivity;
import com.hoaithi.tripme.R;
import com.hoaithi.tripme.SearchPlaceActivity;
import com.hoaithi.tripme.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YourPlanActivity extends AppCompatActivity {
    @BindView(R.id.status_bar)
    View mStatusBar;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;



    YourPlanPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR /*| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR*/);
        getWindow().setNavigationBarColor(Color.WHITE);
        setContentView(R.layout.activity_your_plan);
        ButterKnife.bind(this);

        // add status bar space
        mStatusBar.getLayoutParams().height = Util.getStatusHeight(getResources());
        mStatusBar.requestLayout();

        mPagerAdapter = new YourPlanPagerAdapter(getSupportFragmentManager(),YourPlanPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @OnClick(R.id.back_button)
    void back() {
        finish();
    }

    @OnClick(R.id.floating_add_button)
    void addNewPlan() {
        if(mViewPager.getCurrentItem() ==0)
            startActivity(new Intent(this, SearchPlaceActivity.class));
        else
            startActivity(new Intent(this, AddCost.class));
    }
}
