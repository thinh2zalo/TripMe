package com.hoaithi.tripme.yourplan;

import java.io.Serializable;

public class CostItem implements Serializable {
    public String content;
    public String address;
    public Integer cost;
    public boolean isAdd;
    public String note;

    public CostItem(String content, String address, Integer cost, boolean isAdd, String note){
        this.content = content;
        this.address = address;
        this.cost = cost;
        this.isAdd = isAdd;
        this.note = note;
    }



}
