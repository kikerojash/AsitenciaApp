package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia;

import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia;

import java.util.List;

public interface ReporteAsistenciaView extends BaseActivityView<ReporteAsistenciaPresenter> {
    void mostrarLista(List<Asistencia> asistenciaList);
}
