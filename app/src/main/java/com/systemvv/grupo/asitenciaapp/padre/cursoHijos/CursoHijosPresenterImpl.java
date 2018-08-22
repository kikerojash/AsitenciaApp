package com.systemvv.grupo.asitenciaapp.padre.cursoHijos;

import android.content.res.Resources;
import android.os.Bundle;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.useCase.ObtenerCursoHijos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;

import org.parceler.Parcels;

public class CursoHijosPresenterImpl extends BaseActivityPresenterImpl<CursoHijosView> implements CursoHijosPresenter {

    public static final String TAG = CursoHijosPresenterImpl.class.getSimpleName();

    private ObtenerCursoHijos obtenerCursoHijos;

    public CursoHijosPresenterImpl(UseCaseHandler handler, Resources res, ObtenerCursoHijos obtenerCursoHijos) {
        super(handler, res);
        this.obtenerCursoHijos = obtenerCursoHijos;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    private Hijos hijos;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.hijos = Parcels.unwrap(extras.getParcelable("hijos"));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initListCursosHijos(hijos);
    }

    private void initListCursosHijos(Hijos hijos) {
        if(view!=null)view.mostrarProgressBar();
        handler.execute(obtenerCursoHijos, new ObtenerCursoHijos.RequestValues(hijos),
                new UseCase.UseCaseCallback<ObtenerCursoHijos.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerCursoHijos.ResponseValue response) {
                        if (response.getCursosList() == null) return;
                        if (view != null) {
                            view.ocultarProgressBar();
                            view.mostrarListaCursos(response.getCursosList());
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // if (view != null) view.mostrarListaCursos(hijos.getCursosList());
    }
}
