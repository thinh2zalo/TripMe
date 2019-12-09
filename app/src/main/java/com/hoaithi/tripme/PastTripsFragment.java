package com.hoaithi.tripme;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoaithi.tripme.model.Data;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class PastTripsFragment extends Fragment {


    View mView;
    Unbinder unbinder;

    @OnClick(R.id.floating_add_button)
    void addTrips()
    {
        Intent intent = new Intent(this.getActivity(), AddNewPlanActivity.class);
        startActivity(intent);
    }

    @BindView(R.id.floating_add_button)
    FloatingActionButton addButton;

    @BindView(R.id.trip_list_recycler_view)
    RecyclerView tripsRecyclerView;

    TripItemAdapter tripItemAdapter;

    public PastTripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ongoing_trips, container, false);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }



    public void initView()
    {

        tripItemAdapter = new TripItemAdapter(this.getContext(), Data.trips);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        tripsRecyclerView.setHasFixedSize(true);
        tripsRecyclerView.setLayoutManager(layoutManager);
        tripsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        tripsRecyclerView.setAdapter(tripItemAdapter);

        tripsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    addButton.show();
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && addButton.isShown())
                    addButton.hide();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
