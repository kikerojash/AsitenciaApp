package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas;

import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Tareas;

import java.util.List;

public interface ReporteTareasView extends BaseActivityView<ReporteTareasPresenter> {

    void mostrarListaCursos(List<Tareas> tareasList);

    void fondoVacio();
}
