package com.systemvv.grupo.asitenciaapp.utils;

public class Utils {

    public static final boolean isValideString(String name) {
        return name != null && !name.isEmpty() && !name.trim().isEmpty();
    }
}
