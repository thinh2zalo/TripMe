package com.hoaithi.tripme.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Itinerary implements Serializable {
    public String mName;
    public Date mTimeStart;
    public Date mTimeEnd;
    public int mNumberAttendees;
    public boolean mIsPublic;
    public String mDestination;

    public Itinerary(String name, Date timestart, Date timeend, int people, boolean isPublic, String Destination)
    {
        this.mName = name;
        this.mTimeEnd = timeend;
        this.mTimeStart = timestart;
        this.mNumberAttendees = people;
        this.mIsPublic = isPublic;
        this.mDestination = Destination;
    }

    public Itinerary(Itinerary newItem) {
        this.mName = newItem.mName;
        this.mTimeStart = newItem.mTimeStart;
        this.mTimeEnd = newItem.mTimeEnd;
        this.mNumberAttendees = newItem.mNumberAttendees;
        this.mIsPublic = newItem.mIsPublic;
        this.mDestination = newItem.mDestination;
    }

    @NonNull
    @Override
    public String toString() {
        return this.mName + " " + this.mTimeStart.toString();
    }
}
