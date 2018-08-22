package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.dataSource;

import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Tareas;

import java.util.List;

public interface ReporteTareasDataSource {

    interface CallbackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onObtenerListaTareas(Cursos cursos, CallbackResultado<List<Tareas>> listCallbackResultado);
}
