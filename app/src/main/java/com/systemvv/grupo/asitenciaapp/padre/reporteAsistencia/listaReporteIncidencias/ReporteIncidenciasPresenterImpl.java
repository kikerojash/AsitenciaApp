package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias;

import android.content.res.Resources;

import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.fragment.BaseFragmentPresenterImpl;

public class ReporteIncidenciasPresenterImpl extends BaseFragmentPresenterImpl<ReporteIncidenciasView> implements ReporteIncidenciasPresenter {

    public static final String TAG = ReporteIncidenciasPresenterImpl.class.getSimpleName();

    public ReporteIncidenciasPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }
}
