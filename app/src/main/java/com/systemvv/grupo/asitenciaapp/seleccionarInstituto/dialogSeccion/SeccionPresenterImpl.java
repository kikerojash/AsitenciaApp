package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion;

import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.base.BaseView;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.useCase.ObtenerListaSeccion;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import org.parceler.Parcels;

public class SeccionPresenterImpl implements SeccionPresenter {

    public static final String TAG = SeccionPresenterImpl.class.getSimpleName();

    private SeccionView view;
    private UseCaseHandler handler;
    private ObtenerListaSeccion obtenerListaSeccion;

    public SeccionPresenterImpl(UseCaseHandler handler, ObtenerListaSeccion obtenerListaSeccion) {
        this.handler = handler;
        this.obtenerListaSeccion = obtenerListaSeccion;
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
        if (view != null) view.initVistas(institutoUi);
        initListSeccion();
    }

    private void initListSeccion() {
        // String keyUser = institutoUi.getKeyUsuario();
        String keyPeriodo = institutoUi.getKeyPeriodo();
        String keyProfesor = institutoUi.getKeyUsuario();
        Log.d(TAG, "keyPeriodo " + keyPeriodo + "/ keyUser " + institutoUi.getKeyUsuario());
        handler.execute(obtenerListaSeccion, new ObtenerListaSeccion.RequestValues(keyPeriodo, keyProfesor), new UseCase.UseCaseCallback<ObtenerListaSeccion.ResponseValue>() {
            @Override
            public void onSuccess(ObtenerListaSeccion.ResponseValue response) {
                for (SeccionUi seccionUi : response.getSeccionUiList()) {
                    seccionUi.setInstitutoUi(institutoUi);
                }
                if (view != null) view.mostrarLista(response.getSeccionUiList());
            }

            @Override
            public void onError() {

            }
        });
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

    InstitutoUi institutoUi;

    @Override
    public void onExtras(Bundle bundle) {
        this.institutoUi = Parcels.unwrap(bundle.getParcelable("example"));
        Log.d(TAG, "onExtras : " + institutoUi.getNombre());
        if (view != null) view.initVistas(institutoUi);
    }

    /*@Override
    public void onSeleccionSpinnerGrado(String selectedGrado) {

    }

    @Override
    public void onSeleccionSpinnerSeccion(String selectedSeccion) {

    }*/
}
