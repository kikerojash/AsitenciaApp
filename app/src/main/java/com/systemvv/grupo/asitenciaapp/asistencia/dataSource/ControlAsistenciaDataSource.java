package com.systemvv.grupo.asitenciaapp.asistencia.dataSource;

import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import java.util.List;

public interface ControlAsistenciaDataSource {

    interface ObjectCallbackSuccess<T> {
        void guardarAsistenciaGrupal(T resultado);
    }

    interface ObjectCallbackSuccessAsistencia<T,K> {
        void guardarAsistenciaGrupal(Boolean resultado, List<String> kList,int tipoValidacionFecha);
    }

    void onGuardarAsistenciaLista(List<com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia> asistenciaUiList,CursoUi cursoUi, ObjectCallbackSuccess<Boolean> callbackSuccess);

    void onGuardarAsistenciaListaHoraFin(List<String> asistenciaList, ObjectCallbackSuccess<Boolean> callbackSuccess);

    void onValidarFechaRegistroAsistencia(String fecha,CursoUi cursoUi, ObjectCallbackSuccessAsistencia<Boolean,List<String>> callbackSuccess);

    void onObtenerAlumnosLista(CursoUi cursoUi , ObjectCallbackSuccess<List<Alumnos>> listObjectCallbackSuccess);

}
