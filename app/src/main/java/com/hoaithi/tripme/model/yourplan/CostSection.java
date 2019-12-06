package com.hoaithi.tripme.model.yourplan;

import com.hoaithi.tripme.R;

public class CostSection implements PlanObject {
    public static final int COLOR_ORANGE = R.drawable.background_cost_orange;
    public static final int COLOR_GREEN = R.drawable.background_cost_green;
    public static final int COLOR_BLUE = R.drawable.background_cost_blue;
    public static final int COLOR_RED = R.drawable.background_cost_red;
    public final String mTitle;
    public final String mDescription;
    public final int mBackColor;

    public CostSection(int backColorType, String title, String description) {
        mBackColor = backColorType;
        mTitle = title;
        mDescription = description;
    }
}
