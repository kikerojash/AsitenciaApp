package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;

import org.parceler.Parcels;

public class ReportesPresenterImpl extends BaseActivityPresenterImpl<ReportesView> implements ReportesPresenter {

    public static final String TAG = ReportesPresenterImpl.class.getSimpleName();

    public ReportesPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    private Cursos cursos;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.cursos = Parcels.unwrap(extras.getParcelable("cursosUi"));

        Log.d(TAG, "extras " + cursos.getHijos().getNombre());
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onStart() {
        super.onStart();
        initDatos(cursos);
    }


    private void initDatos(Cursos cursos) {
        if (view != null) {
            view.mostrarDatos(cursos);
            view.initAdapter(cursos);
        }
    }
}
