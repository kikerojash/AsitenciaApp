package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.dataSource;

import com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.dataSource.remote.ReporteAsistenciaRemote;

import java.util.List;

public class ReporteAsistenciaRepository implements ReporteAsistenciaDataSource {

    private ReporteAsistenciaRemote reporteAsistenciaRemote;

    public ReporteAsistenciaRepository(ReporteAsistenciaRemote reporteAsistenciaRemote) {
        this.reporteAsistenciaRemote = reporteAsistenciaRemote;
    }

    @Override
    public void onMostrarListaReporteAsistencia(Cursos cursos, onCallBackResultado<List<Asistencia>> listonCallBackResultado) {
        reporteAsistenciaRemote.onMostrarListaReporteAsistencia(cursos, listonCallBackResultado);
    }
}
