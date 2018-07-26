package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion;

import android.util.Log;

import com.systemvv.grupo.asitenciaapp.base.BaseView;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;

public class SeccionPresenterImpl implements SeccionPresenter {

    public static final String TAG = SeccionPresenterImpl.class.getSimpleName();

    private SeccionView view;
    private UseCaseHandler handler;

    public SeccionPresenterImpl(UseCaseHandler handler) {
        this.handler = handler;
    }

    @Override
    public void attachView(BaseView view) {
        this.view = (SeccionView) view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {
        Log.d(TAG, "OnStart");
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onSeleccionSpinnerGrado(String selectedGrado) {

    }

    @Override
    public void onSeleccionSpinnerSeccion(String selectedSeccion) {

    }
}
