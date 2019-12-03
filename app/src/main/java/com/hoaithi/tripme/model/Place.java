package com.hoaithi.tripme.model;

public final class Place {
    public final Object mSource;
    public final String mTitle;

    public Object getSource() {
        return mSource;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public final String mDescription;

    public Place(Object source, String title, String description) {
        mSource = source;
        mTitle = title;
        mDescription = description;
    }
}
