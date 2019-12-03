package com.hoaithi.tripme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hoaithi.tripme.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set light theme with transparent status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR /*| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR*/);
        getWindow().setNavigationBarColor(Color.WHITE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        // add status bar space
//        mStatusBar.getLayoutParams().height = Util.getStatusHeight(getResources());
//        mStatusBar.requestLayout();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        DiscoverFragment discoverFragment = new DiscoverFragment();
        TripsFragment tripsFragment = new TripsFragment();

    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId())
            {
                case R.id.khampha_nav:
                    selectedFragment = new DiscoverFragment();
                    break;
                case R.id.lichtrinh_nav:
                    selectedFragment = new TripsFragment();
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame, selectedFragment).commit();
            return true;
        }
    };

//    @BindView(R.id.status_bar)
//    View mStatusBar;
}
