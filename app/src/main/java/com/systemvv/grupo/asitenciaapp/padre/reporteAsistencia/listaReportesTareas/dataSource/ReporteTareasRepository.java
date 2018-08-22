package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.dataSource;

import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Tareas;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.dataSource.remote.ReporteTareasRemote;

import java.util.List;

public class ReporteTareasRepository implements ReporteTareasDataSource {

    private ReporteTareasRemote reporteTareasRemote;

    public ReporteTareasRepository(ReporteTareasRemote reporteTareasRemote) {
        this.reporteTareasRemote = reporteTareasRemote;
    }

    @Override
    public void onObtenerListaTareas(Cursos cursos, CallbackResultado<List<Tareas>> listCallbackResultado) {
        reporteTareasRemote.onObtenerListaTareas(cursos, listCallbackResultado);
    }
}
