package com.hoaithi.tripme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.hoaithi.tripme.model.Data;
import com.hoaithi.tripme.model.Itinerary;
import com.hoaithi.tripme.util.Util;
import com.travijuu.numberpicker.library.NumberPicker;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewPlanActivity extends AppCompatActivity {

    private static final int MAX_LENGTH = 20;
    DatePickerDialog datePickerDialog;

    @BindView(R.id.status_bar)
    View mStatusBar;

    @BindView(R.id.tenchuyendi_edit_text)
    EditText tenchuyendiEditText;

    @BindView(R.id.diemden_edit_text)
    AutoCompleteTextView diemdenEditText;

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


    public Itinerary getNewTrip() throws ParseException {
        String name = tenchuyendiEditText.getText().toString();

        String destination = diemdenEditText.getText().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = new Date(), endDate = new Date();
        startDate = formatter.parse(khoihanhEditText.getText().toString());
        endDate = formatter.parse(ngayveEditText.getText().toString());

        int attendee = (soluongNumberPicker.getValue());
        boolean isPublic = chedoSwitch.isChecked();
        return new Itinerary(name, startDate, endDate, attendee, isPublic, destination);
    }

    public boolean validateNameTrip()
    {
        if (tenchuyendiEditText.getText().toString().length() == 0)
        {
            tenchuyendiEditText.setError("Không được bỏ trống");
            return false;
        }
        else if (tenchuyendiEditText.getText().toString().length() >= MAX_LENGTH)
        {
            tenchuyendiEditText.setError("Tên quá dài. Chiều dài tối đa là 20 kí tự");
            return false;
        }
        tenchuyendiEditText.setError(null);
        return true;
    }

    public boolean validateDestination()
    {
        if (diemdenEditText.getText().toString().length() == 0)
        {
            diemdenEditText.setError("Không được bỏ trống");
            return false;
        }
        diemdenEditText.setError(null);
        return true;
    }

    public boolean validateStartDate()
    {
        if (khoihanhEditText.getText().toString().length() == 0)
        {
            khoihanhEditText.setError("Không được bỏ trống");
            return false;
        }
        khoihanhEditText.setError(null);
        return true;
    }

    public boolean validateEndDate()
    {
        if (ngayveEditText.getText().toString().length() == 0)
        {
            ngayveEditText.setError("Không được bỏ trống");
            return false;
        }
        ngayveEditText.setError(null);
        return true;
    }

    public boolean validateValueDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date startDate = formatter.parse(khoihanhEditText.getText().toString());
            Date endDate = formatter.parse(ngayveEditText.getText().toString());

            if (startDate.before(new Date()))
            {
                khoihanhEditText.setError("Ngày tạo phải sau ngày hiện tại");
                ngayveEditText.setError(null);
                return false;
            }

            if (startDate.after(endDate))
            {
                khoihanhEditText.setError("Ngày đi phải sau ngày về.");
                ngayveEditText.setError(null);
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        khoihanhEditText.setError(null);
        ngayveEditText.setError(null);
        return true;
    }

    @OnClick(R.id.xong_button)
    void createNewTrip() {

        if (!validateNameTrip() || !validateStartDate() || !validateEndDate() || !validateValueDate())
            return;

        Itinerary newItem = null;
        try {
            newItem = getNewTrip();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Data.addNewTrip(newItem);
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
                        String str = String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year);
                        khoihanhEditText.setText(str);
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
                        String str = String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year);
                        ngayveEditText.setText(str);
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

        diemdenEditText.setAdapter(new PlaceAutoSuggestAdapter(AddNewPlanActivity.this, android.R.layout.simple_list_item_1));
    }
}
