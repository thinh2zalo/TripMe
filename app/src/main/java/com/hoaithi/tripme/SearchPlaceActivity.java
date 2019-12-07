package com.hoaithi.tripme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import com.hoaithi.tripme.model.Attraction;
import com.hoaithi.tripme.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchPlaceActivity extends AppCompatActivity {

    @BindView(R.id.status_bar)
    View mStatusBar;


    @BindView(R.id.attraction_recycler_view)
    RecyclerView attractionRecyclerView;

    AttractionItemAdapter attractionItemAdapter;
    List<Attraction> attractionList = new ArrayList<>();
    PlaceApi placeApi = new PlaceApi();

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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getData();
        initView();
    }

    private void getData() {
        attractionList = placeApi.getAttractionInCity("Đà Lạt");
    }

    private void initView() {
        Log.d("Nunu", Integer.toString(attractionList.size()));
        attractionItemAdapter = new AttractionItemAdapter(this, attractionList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        attractionRecyclerView.setHasFixedSize(true);
        attractionRecyclerView.setLayoutManager(layoutManager);
        attractionRecyclerView.setItemAnimator(new DefaultItemAnimator());
        attractionRecyclerView.setAdapter(attractionItemAdapter);
    }
}
