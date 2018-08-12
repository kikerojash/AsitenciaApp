package com.systemvv.grupo.asitenciaapp.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static final boolean isValideString(String name) {
        return name != null && !name.isEmpty() && !name.trim().isEmpty();
    }

    public static String convertTime(long time){
        Date date = new Date(time);
        //Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        Format format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }
}
