package com.hoaithi.tripme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.hoaithi.tripme.model.Attraction;
import com.hoaithi.tripme.model.Place;
import com.hoaithi.tripme.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchPlaceActivity extends AppCompatActivity  {

    @BindView(R.id.status_bar)
    View mStatusBar;
    @BindView(R.id.city_text_view)
    TextView cityTextView;
    @BindView(R.id.tl_searchPlace)
    TabLayout searchPlaceTab;
    @BindView(R.id.vp_seachPlace)
    ViewPager searchPlacePager;

    public static String city;

    @OnClick(R.id.back_button)
    void back() {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setNavigationBarColor(Color.WHITE);
        setContentView(R.layout.activity_search_place);
        ButterKnife.bind(this);

        // add status bar space
        mStatusBar.getLayoutParams().height = Util.getStatusHeight(getResources());
        mStatusBar.requestLayout();
        initView();
    }

    private void initView() {

        createTab();
        if (getIntent().getStringExtra("city") != null)
        {
            city = getIntent().getStringExtra("city");
            cityTextView.setText(city);
        }
        else
        {
            city = cityTextView.getText().toString();
        }

    }

    private void createTab() {
        searchPlacePager.setAdapter(new SearchPlacePagerAdapter(getSupportFragmentManager()));
        searchPlaceTab.setupWithViewPager(searchPlacePager);
        searchPlacePager.setCurrentItem(0);
    }



}
