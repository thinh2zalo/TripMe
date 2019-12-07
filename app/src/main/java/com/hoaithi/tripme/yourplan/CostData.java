package com.hoaithi.tripme.yourplan;


import com.hoaithi.tripme.model.yourplan.ItemCostDetail;
import com.hoaithi.tripme.model.yourplan.PlanObject;

import java.util.ArrayList;

public class CostData {

    public static YourPlanAdapter mAdapter;
    public static ArrayList<PlanObject> list = new ArrayList<>();



    public static void addCostItem(CostItem costItem)
    {
        if(costItem.isAdd )
            list.add(new ItemCostDetail(ItemCostDetail.TYPE_ADD,costItem.content,costItem.address,costItem.cost.toString()));
        else
            list.add(new ItemCostDetail(ItemCostDetail.TYPE_REMOVE,costItem.content,costItem.address,costItem.cost.toString()));

        mAdapter.setData(list);

    }
}
