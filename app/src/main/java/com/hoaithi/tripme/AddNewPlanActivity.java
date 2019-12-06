package com.hoaithi.tripme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.hoaithi.tripme.util.Util;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewPlanActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;

    @BindView(R.id.status_bar)
    View mStatusBar;

    @BindView(R.id.tenchuyendi_edit_text)
    EditText tenchuyendiEditText;

    @BindView(R.id.khoihanh_edit_text)
    EditText khoihanhEditText;

    @BindView(R.id.ngayve_edit_text)
    EditText ngayveEditText;

    @BindView(R.id.soluongnguoi_number_picker)
    NumberPicker soluongNumberPicker;

    @BindView(R.id.chedo_switch)
    SwitchMaterial chedoSwitch;

    @OnClick(R.id.back_button)
    void back() {
        finish();
    }


    @OnClick(R.id.khoihanh_edit_text)
    public void khoihanhOnClick()
    {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        datePickerDialog = new DatePickerDialog(AddNewPlanActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        khoihanhEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    @OnClick(R.id.ngayve_edit_text)
    public void ngayveOnClick()
    {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        datePickerDialog = new DatePickerDialog(AddNewPlanActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        ngayveEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set light theme with transparent status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setNavigationBarColor(Color.WHITE);
        setContentView(R.layout.activity_add_new_plan);
        ButterKnife.bind(this);

        initView();
    }

    void initView()
    {
        // add status bar space
        mStatusBar.getLayoutParams().height = Util.getStatusHeight(getResources());
        mStatusBar.requestLayout();

        khoihanhEditText.setInputType(InputType.TYPE_NULL);
        ngayveEditText.setInputType(InputType.TYPE_NULL);

        soluongNumberPicker.setMin(1);
        soluongNumberPicker.setMax(20);
        soluongNumberPicker.setValue(1);
    }
}
