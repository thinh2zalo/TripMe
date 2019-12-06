package com.hoaithi.tripme.model;

import com.hoaithi.tripme.TripItemAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Data {
    public static ArrayList<Itinerary> trips = new ArrayList<>();
    public static TripItemAdapter tripItemAdapter;

    public static void addNewTrip(Itinerary newItem)
    {
        trips.add(new Itinerary(newItem));
        Collections.sort(trips, new Comparator<Itinerary>() {
            @Override
            public int compare(Itinerary a, Itinerary b) {
                return a.mTimeStart.compareTo(b.mTimeStart);
            }
        });

        if (Data.tripItemAdapter != null)
        {
            tripItemAdapter.notifyDataSetChanged();
        }
    }
}
