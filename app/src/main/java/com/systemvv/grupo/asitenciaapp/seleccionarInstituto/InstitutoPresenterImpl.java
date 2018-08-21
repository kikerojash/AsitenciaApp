package com.systemvv.grupo.asitenciaapp.seleccionarInstituto;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.useCase.ObtenerInstitutoLista;

import org.parceler.Parcels;


public class InstitutoPresenterImpl extends BaseActivityPresenterImpl<InstitutoView> implements InstitutoPresenter {

    public static final String TAG = InstitutoPresenterImpl.class.getSimpleName();

    private ObtenerInstitutoLista obtenerInstitutoLista;

    public InstitutoPresenterImpl(UseCaseHandler handler, Resources res, ObtenerInstitutoLista obtenerInstitutoLista) {
        super(handler, res);
        this.obtenerInstitutoLista = obtenerInstitutoLista;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        obtenerDatosDocente();
    }

    private void obtenerDatosDocente() {
    }

    String keyPeriodo;
    UsuarioUi usuarioUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.usuarioUi = Parcels.unwrap(extras.getParcelable("usuarioUi"));
        this.keyPeriodo = usuarioUi.getKeyPeriodo();
        //this.keyPeriodo = extras.getString("keyPeriodo");
        Log.d(TAG, "usuario : " + usuarioUi.getKeyUser()+
                "usuario : " + usuarioUi.getUsu_email()
                + " / keyPeriodo : " + keyPeriodo);
    }

    @Override
    public void onStart() {
        super.onStart();
        mostrarListaInstitutos(keyPeriodo);
        // if (view != null) view.mostrarListaInstitutos(insertDataStatica());
    }

    private void mostrarListaInstitutos(final String keyPeriodo) {
        if (view != null) view.mostrarProgressBar();
        handler.execute(obtenerInstitutoLista, new ObtenerInstitutoLista.RequestValues(keyPeriodo),
                new UseCase.UseCaseCallback<ObtenerInstitutoLista.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerInstitutoLista.ResponseValue response) {
                        if (view != null) {
                            for (InstitutoUi institutoUi : response.getInstitutoUiList()) {
                                institutoUi.setKeyPeriodo(keyPeriodo);
                                institutoUi.setKeyUsuario(usuarioUi.getKeyUser());
                            }
                            view.mostrarListaInstitutos(response.getInstitutoUiList());
                            //view.mostrarListaInstitutos(insertDataStatica());
                            view.ocultarProgressBar();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

}
