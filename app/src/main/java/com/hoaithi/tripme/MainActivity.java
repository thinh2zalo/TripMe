package com.hoaithi.tripme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.hoaithi.tripme.model.Place;
import com.hoaithi.tripme.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nearby_recycler_view)
    RecyclerView mNearbyRecyclerView;

    @BindView(R.id.popular_recycler_view)
    RecyclerView mPopularRecyclerView;

    private PlaceAdapter mNearbyAdapter;
    private PlaceAdapter mPopularAdapter;

    @OnClick(R.id.make_plan)
    void addNewPlan() {
        startActivity(new Intent(this,AddNewPlanActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set light theme with transparent status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR /*| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR*/);
        getWindow().setNavigationBarColor(Color.WHITE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // add status bar space
        mStatusBar.getLayoutParams().height = Util.getStatusHeight(getResources());
        mStatusBar.requestLayout();

        mNearbyAdapter = new PlaceAdapter(this);
        mPopularAdapter = new PlaceAdapter(this);

        mNearbyRecyclerView.setAdapter(mNearbyAdapter);
        mNearbyRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        mPopularRecyclerView.setAdapter(mPopularAdapter);
        mPopularRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        refreshData();
    }

    public void refreshData() {
        ArrayList<Place> list1 = new ArrayList<>();
        list1.add(new Place(R.drawable.hand,"Cầu Vàng","Đà Nẵng"));
        list1.add(new Place(R.drawable.kien_giang,"Du Lịch Kiên Giang","Kiên Giang"));
        list1.add(new Place(R.drawable.hand,"Cầu Vàng","Đà Nẵng"));
        list1.add(new Place(R.drawable.kien_giang,"Du Lịch Kiên Giang","Kiên Giang"));
        list1.add(new Place(R.drawable.hand,"Cầu Vàng","Đà Nẵng"));


        ArrayList<Place> list2 = new ArrayList<>();
        list2.add(new Place(R.drawable.lotus_pond,"Lotus Pond","Kaohsiung"));
        list2.add(new Place(R.drawable.suwon,"Du lịch Suwon","Suwon, Hàn Quốc"));
        list2.add(new Place(R.drawable.lotus_pond,"Lotus Pond","Kaohsiung"));
        list2.add(new Place(R.drawable.suwon,"Du lịch Suwon","Suwon, Hàn Quốc"));
        list2.add(new Place(R.drawable.lotus_pond,"Lotus Pond","Kaohsiung"));

        mNearbyAdapter.setData(list1);
        mPopularAdapter.setData(list2);
    }

    @BindView(R.id.status_bar)
    View mStatusBar;
}
