package com.hoaithi.tripme.model.yourplan;

public class DrivingTimeline implements PlanObject {
    public static final int TYPE_MOTOR = 0;
    public static final int TYPE_CAR = 1;
    public final int mType;
    public final String mDescription;

    public DrivingTimeline(int type, String description) {
        mType = type;
        mDescription = description;
    }
}
