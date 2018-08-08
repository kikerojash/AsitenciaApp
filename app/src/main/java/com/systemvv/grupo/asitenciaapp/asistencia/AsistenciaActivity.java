package com.systemvv.grupo.asitenciaapp.asistencia;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.R;

public class AsistenciaActivity extends AppCompatActivity {

    public static final String TAG = AsistenciaActivity.class.getSimpleName();
    String keyInstituto, keyCurso, keyUser, keyPeriodo, keySeccion, keyGrado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);
        Bundle extras = getIntent().getExtras();
        keyInstituto = extras.getString("keyInstituto");
        keyCurso = extras.getString("keyCurso");
        keyUser = extras.getString("keyUser");
        keyPeriodo = extras.getString("keyPeriodo");
        keySeccion = extras.getString("keySeccion");
        keyGrado = extras.getString("keyGrado");
        Log.d(TAG, "keyInstituto / " + keyInstituto +
                " / keyCurso " + keyCurso +
                " / keyUser " + keyUser +
                " / keyPeriodo " + keyPeriodo +
                " / keySeccion " + keySeccion +
                " / keyGrado " + keyGrado
        );
    }

/*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString("keyInstituto", keyInstituto);
        savedInstanceState.putString("keyCurso", keyCurso);
        savedInstanceState.putString("keyUser", keyUser);
        savedInstanceState.putString("keyPeriodo", keyPeriodo);
        savedInstanceState.putString("keySeccion", keySeccion);
        savedInstanceState.putString("keyGrado", keyGrado);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy

        String keyInstituto = savedInstanceState.getString("keyInstituto");
        String keyCurso = savedInstanceState.getString("keyCurso");
        String keyUser = savedInstanceState.getString("keyUser");
        String keyPeriodo = savedInstanceState.getString("keyPeriodo");
        String keySeccion = savedInstanceState.getString("keySeccion");
        String keyGrado = savedInstanceState.getString("keyGrado");
        Log.d(TAG, "onRestoreInstanceState / " + keyInstituto +
                " / keyCurso " + keyCurso +
                " / keyUser " + keyUser +
                " / keyPeriodo " + keyPeriodo +
                " / keySeccion " + keySeccion +
                " / keyGrado " + keyGrado
        );
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state members from saved instance

    }*/
}
