package com.hoaithi.tripme.model;

import java.util.ArrayList;

public class Data {
    public static ArrayList<Itinerary> trips = new ArrayList<>();

    public static void addNewTrip(Itinerary newItem)
    {
        trips.add(newItem);
    }
}
