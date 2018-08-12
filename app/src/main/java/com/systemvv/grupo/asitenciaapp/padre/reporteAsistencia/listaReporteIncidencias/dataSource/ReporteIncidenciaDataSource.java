package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.dataSource;

import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;

import java.util.List;

public interface ReporteIncidenciaDataSource {

    interface onCallBackResultado<T> {
        void onResultado(T resultado);
    }

    void onMostrarListaReporteIncidencia(Cursos cursos, onCallBackResultado<List<Incidencias>> listonCallBackResultado);

}
