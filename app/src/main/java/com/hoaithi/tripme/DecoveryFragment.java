package com.hoaithi.tripme;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoaithi.tripme.model.Place;
import com.hoaithi.tripme.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class DecoveryFragment extends Fragment {


    public DecoveryFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new DecoveryFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @BindView(R.id.nearby_recycler_view)
    RecyclerView mNearbyRecyclerView;

    @BindView(R.id.popular_recycler_view)
    RecyclerView mPopularRecyclerView;

    private PlaceAdapter mNearbyAdapter;
    private PlaceAdapter mPopularAdapter;

    @OnClick(R.id.make_plan)
    void addNewPlan() {
        startActivity(new Intent(getContext(), AddNewPlanActivity.class));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        // add status bar space
        mStatusBar.getLayoutParams().height = Util.getStatusHeight(getResources());
        mStatusBar.requestLayout();

        mNearbyAdapter = new PlaceAdapter(getContext());
        mPopularAdapter = new PlaceAdapter(getContext());

        mNearbyRecyclerView.setAdapter(mNearbyAdapter);
        mNearbyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        mPopularRecyclerView.setAdapter(mPopularAdapter);
        mPopularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

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
