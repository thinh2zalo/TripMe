package com.hoaithi.tripme.model.yourplan;

public class ItemCostDetail implements PlanObject {
    public static final int TYPE_ADD = 0;
    public static final int TYPE_REMOVE = 1;
    public final int mType;
    public final String mTItle;
    public final String mDescription;

    public ItemCostDetail(int type, String TItle, String description, String cost) {
        mType = type;
        mTItle = TItle;
        mDescription = description;
        mCost = cost;
    }

    public final String mCost;
}
