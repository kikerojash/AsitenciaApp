package com.systemvv.grupo.asitenciaapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static final boolean isValideString(String name) {
        return name != null && !name.isEmpty() && !name.trim().isEmpty();
    }

    public static String convertTime(long time){
        Date date = new Date(time);
        //Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        Format format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static String capitalize(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
