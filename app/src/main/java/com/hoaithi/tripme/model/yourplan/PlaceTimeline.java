package com.hoaithi.tripme.model.yourplan;

public class PlaceTimeline implements PlanObject {
    public final String mTimeFrom;
    public final String mTimeTo;
    public final String mTitle;

    public PlaceTimeline(String timeFrom, String timeTo, String title, String cost) {
        mTimeFrom = timeFrom;
        mTimeTo = timeTo;
        mTitle = title;
        mCost = cost;
    }

    public final String mCost;
}
