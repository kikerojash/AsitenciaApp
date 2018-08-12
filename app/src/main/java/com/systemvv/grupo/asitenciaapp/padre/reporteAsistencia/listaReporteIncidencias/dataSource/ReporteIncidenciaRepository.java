package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.dataSource;

import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.dataSource.remote.ReporteIncidenciaRemote;

import java.util.List;

public class ReporteIncidenciaRepository implements ReporteIncidenciaDataSource {

    private ReporteIncidenciaRemote reporteIncidenciaRemote;

    public ReporteIncidenciaRepository(ReporteIncidenciaRemote reporteIncidenciaRemote) {
        this.reporteIncidenciaRemote = reporteIncidenciaRemote;
    }

    @Override
    public void onMostrarListaReporteIncidencia(Cursos cursos, onCallBackResultado<List<Incidencias>> listonCallBackResultado) {
        reporteIncidenciaRemote.onMostrarListaReporteIncidencia(cursos, listonCallBackResultado);
    }
}
