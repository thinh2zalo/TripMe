package com.hoaithi.tripme;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoaithi.tripme.model.Data;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class OngoingTripsFragment extends Fragment {

    View mView;
    Unbinder unbinder;

    @BindView(R.id.trip_list_recycler_view)
    RecyclerView tripsRecyclerView;

    TripItemAdapter tripItemAdapter;

    public OngoingTripsFragment() {
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
        tripsRecyclerView.setLayoutManager(layoutManager);
        tripsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        tripsRecyclerView.setAdapter(tripItemAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
