package com.hoaithi.tripme.util;

import android.content.res.Resources;

public final class Util {
    public static int StatusHeight = -1;

    public static int getStatusHeight(Resources myR) {
        if (StatusHeight != -1) return StatusHeight;
        int height;
        int idSbHeight = myR.getIdentifier("status_bar_height", "dimen", "android");
        if (idSbHeight > 0) {
            height = myR.getDimensionPixelOffset(idSbHeight);
            //   Toast.makeText(this, "Status Bar Height = "+ height, Toast.LENGTH_SHORT).show();
        } else {
            height = 0;
            //        Toast.makeText(this,"Resources NOT found",Toast.LENGTH_LONG).show();
        }
        StatusHeight = height;
        return StatusHeight;
    }
}
