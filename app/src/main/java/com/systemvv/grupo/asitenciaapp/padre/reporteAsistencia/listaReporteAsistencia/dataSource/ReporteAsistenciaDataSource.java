package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.dataSource;

import com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;

import java.util.List;

public interface ReporteAsistenciaDataSource {

    interface onCallBackResultado<T> {
        void onResultado(T resultado);
    }

    void onMostrarListaReporteAsistencia(Cursos cursos, onCallBackResultado<List<Asistencia>> listonCallBackResultado);
}
