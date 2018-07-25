package com.systemvv.grupo.asitenciaapp;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;

public class AsistenciaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // MultiDex.install(NoticiasAplicacion.this);
    }
}
