package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia;

import android.content.res.Resources;

import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;

public class ReporteAsistenciaPresenterImpl extends BaseActivityPresenterImpl<ReporteAsistenciaView> implements ReporteAsistenciaPresenter {

    public static final String TAG = ReporteAsistenciaPresenterImpl.class.getSimpleName();

    public ReporteAsistenciaPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }
}
