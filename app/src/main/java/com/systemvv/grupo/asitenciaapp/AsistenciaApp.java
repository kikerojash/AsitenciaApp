package com.systemvv.grupo.asitenciaapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.firebase.FirebaseApp;

public class AsistenciaApp extends Application {

    public static AsistenciaApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FirebaseApp.initializeApp(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(AsistenciaApp.this);
    }

    public static AsistenciaApp getmInstance(){
        return mInstance;
    }
}
