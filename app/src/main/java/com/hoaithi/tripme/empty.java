package com.hoaithi.tripme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hoaithi.tripme.ui.empty.EmptyFragment;

public class empty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EmptyFragment.newInstance())
                    .commitNow();
        }
    }
}
