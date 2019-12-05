package com.hoaithi.tripme.model.yourplan;

public class DateSection implements PlanObject {
    public final String mTitle;

    public DateSection(String title, String description) {
        mTitle = title;
        mDescription = description;
    }

    public final String mDescription;
}
