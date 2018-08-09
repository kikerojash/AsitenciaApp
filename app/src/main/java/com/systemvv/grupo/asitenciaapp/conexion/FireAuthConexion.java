package com.systemvv.grupo.asitenciaapp.conexion;

import com.google.firebase.auth.FirebaseAuth;

public class FireAuthConexion {

    private static FirebaseAuth instance;
    // private static FirebaseAuth   firebaseAuth;

    public static FirebaseAuth getInstance() {
        if (instance == null) {
            instance = FirebaseAuth.getInstance();
        }
        return instance;
    }
}
