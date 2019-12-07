package com.hoaithi.tripme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hoaithi.tripme.R;
import com.hoaithi.tripme.model.Data;
import com.hoaithi.tripme.model.yourplan.ItemCostDetail;
import com.hoaithi.tripme.util.Util;
import com.hoaithi.tripme.yourplan.CostData;
import com.hoaithi.tripme.yourplan.CostItem;
import com.hoaithi.tripme.yourplan.YourCostFragment;

import java.text.ParseException;


public class AddCost extends AppCompatActivity {

    @BindView(R.id.content_tiet)
    TextInputEditText content_tiet;

    @BindView(R.id.address_tiet)
    TextInputEditText address_tiet;

    @BindView(R.id.congtruchiphi)
    ToggleButton addPlusToggle;

    @BindView(R.id.cost_tiet)
    TextInputEditText cost_tiet;

    @BindView(R.id.note_tiet)
    TextInputEditText note_tiet;

    @BindView(R.id.save_btn)
    Button savebtn;

    @BindView(R.id.cancel_btn)
    Button cancelbtn;

    public boolean validateContent(){
        if(content_tiet.getText().toString().length()==0)
        {
            content_tiet.setError("Nội dung không được để trống");
            return false;
        }

        else
            content_tiet.setError(null);
        return true;

    }

    public boolean validateDiaDiem(){
        if(address_tiet.getText().toString().length()==0){
            address_tiet.setError("Địa điểm không được để trống");
            return false;
        }
        else
            address_tiet.setError(null);
        return true;
    }

    public boolean validateCapNhatChiPhi(){
        if(cost_tiet.getText().toString().length()==0){
            cost_tiet.setError("Chi phí không được để trống");
            return false;
        }
        else cost_tiet.setError(null);
        return true;
    }


    public CostItem getNewCost()  {
        String content = content_tiet.getText().toString();
        String address = address_tiet.getText().toString();
        String note = note_tiet.getText().toString();
        Integer cost = Integer.parseInt( cost_tiet.getText().toString());

        boolean isAdd = addPlusToggle.getText() == "+";


        return new CostItem(content,address,cost,isAdd,note);
    }

    @OnClick(R.id.save_btn)
    public void updateCost(){


        if (!validateCapNhatChiPhi() || !validateContent() || !validateDiaDiem() ) {
            System.out.println("validate " + validateDiaDiem() + validateContent() + validateCapNhatChiPhi());
            return;
        }

        CostItem costItem = null;
        costItem = getNewCost();

        if(costItem.isAdd )
            YourCostFragment.list.add(new ItemCostDetail(ItemCostDetail.TYPE_ADD,costItem.content,costItem.address,costItem.cost.toString()));
        else
            YourCostFragment.list.add(new ItemCostDetail(ItemCostDetail.TYPE_REMOVE,costItem.content,costItem.address,costItem.cost.toString()));
        YourCostFragment.refreshData(true);
        finish();



    }

    @OnClick(R.id.cancel_btn)
    void back() {
        finish();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);

        ButterKnife.bind(this);

    }
}
