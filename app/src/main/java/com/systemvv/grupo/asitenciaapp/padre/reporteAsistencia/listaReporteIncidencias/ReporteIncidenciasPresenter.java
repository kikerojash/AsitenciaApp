package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias;

import com.systemvv.grupo.asitenciaapp.base.fragment.BaseFragmentPresenter;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;

public interface ReporteIncidenciasPresenter extends BaseFragmentPresenter<ReporteIncidenciasView> {

    void onEliminarClick(Incidencias incidencias);
}
