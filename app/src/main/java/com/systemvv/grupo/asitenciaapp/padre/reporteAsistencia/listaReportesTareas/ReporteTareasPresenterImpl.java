package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas;

import android.content.res.Resources;

import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.fragment.BaseFragmentPresenterImpl;

public class ReporteTareasPresenterImpl extends BaseFragmentPresenterImpl<ReporteTareasView> implements ReporteTareasPresenter{



    public ReporteTareasPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onActivityCreated() {

    }
}
