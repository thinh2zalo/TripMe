package com.hoaithi.tripme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hoaithi.tripme.discovertab.BottomPagerAdapter;
import com.hoaithi.tripme.model.Data;
import com.hoaithi.tripme.model.Itinerary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.view_pager)
    ViewPager mBottomPager;

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;

    BottomPagerAdapter mBottomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set light theme with transparent status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR /*| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR*/);
        getWindow().setNavigationBarColor(Color.WHITE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        vibrator  = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        mBottomAdapter = new BottomPagerAdapter(this, getSupportFragmentManager());
        mBottomPager.setAdapter(mBottomAdapter);
        mBottomPager.setOffscreenPageLimit(5);
        mBottomPager.addOnPageChangeListener(this);

        int page = getIntent().getIntExtra("page",0);
        mBottomPager.setCurrentItem(page);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        try {
            dataInit();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        super.onStart();
    }

    public void dataInit() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Itinerary sample = new Itinerary("Sample Trip", sdf.parse("01/12/2019"),
                sdf.parse("05/12/2019"), 3, false, "Đà Lạt");
        Data.addNewTrip(sample);
    }

    Vibrator vibrator;
    private void vibrate() {
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(25);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==0) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR /*| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR*/);
            getWindow().setNavigationBarColor(Color.WHITE);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setNavigationBarColor(Color.WHITE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.khampha_nav:
                vibrate();
                mBottomPager.setCurrentItem(0);
                return true;
            case R.id.lichtrinh_nav:
                vibrate();
                mBottomPager.setCurrentItem(1);
                return true;
            case R.id.cuuho_nav:
                vibrate();
                mBottomPager.setCurrentItem(2);
                return true;
            case R.id.thongbao_nav:
                vibrate();
                mBottomPager.setCurrentItem(3);
                return true;
            case R.id.khac_nav:
                vibrate();
                mBottomPager.setCurrentItem(4);
                return true;
        }
        return false;
    }
}