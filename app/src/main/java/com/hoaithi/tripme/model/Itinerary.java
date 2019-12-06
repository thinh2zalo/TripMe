package com.hoaithi.tripme.model;

import java.util.Date;

public class Itinerary {
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
}
