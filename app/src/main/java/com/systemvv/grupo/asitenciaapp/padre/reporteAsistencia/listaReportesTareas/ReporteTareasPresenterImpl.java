package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.fragment.BaseFragmentPresenterImpl;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.useCase.ObtenerTareasLista;

import org.parceler.Parcels;

public class ReporteTareasPresenterImpl extends BaseFragmentPresenterImpl<ReporteTareasView> implements ReporteTareasPresenter {


    public static final String TAG = ReporteTareasPresenterImpl.class.getSimpleName();

    Cursos cursos;
    ObtenerTareasLista obtenerTareasLista;

    public ReporteTareasPresenterImpl(UseCaseHandler handler, Resources res, ObtenerTareasLista obtenerTareasLista) {
        super(handler, res);
        this.obtenerTareasLista = obtenerTareasLista;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.cursos = Parcels.unwrap(extras.getParcelable("cursosUi"));
        initListaTareas(cursos);
    }

    private void initListaTareas(Cursos cursos) {
        handler.execute(obtenerTareasLista, new ObtenerTareasLista.RequestValues(cursos),
                new UseCase.UseCaseCallback<ObtenerTareasLista.ResponseValues>() {
                    @Override
                    public void onSuccess(ObtenerTareasLista.ResponseValues response) {
                        if (response.getTareasList() == null) {
                            Log.d(TAG,"response.getTareasList() == null ");
                            if(view!=null) view.fondoVacio();return;
                        }
                        if (response.getTareasList().size() > 0) {
                            if (view != null) view.mostrarListaCursos(response.getTareasList());
                        } else {
                            if (view != null) view.fondoVacio();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
