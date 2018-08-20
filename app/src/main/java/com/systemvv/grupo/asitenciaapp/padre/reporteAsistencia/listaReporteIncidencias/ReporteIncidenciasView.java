package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias;

import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;

import java.util.List;

public interface ReporteIncidenciasView extends BaseActivityView<ReporteIncidenciasPresenter> {

    void mostrarLista(List<Incidencias> incidenciasList);

    void eliminarItem(Incidencias incidencias);
}
