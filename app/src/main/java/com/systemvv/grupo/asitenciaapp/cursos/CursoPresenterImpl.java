package com.systemvv.grupo.asitenciaapp.cursos;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.cursos.useCase.ObtenerCursoLista;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;
import org.parceler.Parcels;



public class CursoPresenterImpl extends BaseActivityPresenterImpl<CursoView> implements CursoPresenter {

    public static final String TAG = CursoPresenterImpl.class.getSimpleName();


    private ObtenerCursoLista obtenerCursoLista;

    public CursoPresenterImpl(UseCaseHandler handler, Resources res, ObtenerCursoLista obtenerCursoLista) {
        super(handler, res);
        this.obtenerCursoLista = obtenerCursoLista;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    int gradoSelected;
    String seccionSelected;
    //  InstitutoUi institutoUi;
    SeccionUi seccionUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;

       /* this.gradoSelected = extras.getInt("gradoSelected", 0);
        seccionSelected = extras.getString("seccionSelected");*/
        //institutoUi = Parcels.unwrap(extras.getParcelable("instituto"));
        this.seccionUi =  Parcels.unwrap(extras.getParcelable("seccionUi"));
        Log.d(TAG, "institutoUi : " + seccionUi.getInstitutoUi().getKeyUsuario());
    }

    @Override
    public void onStart() {
        super.onStart();
        initListCursos(seccionUi);
        // if (view != null) view.mostrarListaCursos(getCursoList());
    }

    private void initListCursos(SeccionUi seccionUi) {
        if(view!=null)view.mostrarProgressBar();
        handler.execute(obtenerCursoLista, new ObtenerCursoLista.RequestValues(seccionUi),
                new UseCase.UseCaseCallback<ObtenerCursoLista.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerCursoLista.ResponseValue response) {
                        if (view != null){
                            view.mostrarListaCursos(response.getCursoUiList());
                            view.ocultarProgressBar();
                        }

                    }

                    @Override
                    public void onError() {

                    }
                });
    }


}
