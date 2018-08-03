package com.systemvv.grupo.asitenciaapp.asistencia.dataSource;

import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Asistencia;

import java.util.List;

public interface ControlAsistenciaDataSource {

    interface ObjectCallbackSuccess<T> {
        void guardarAsistenciaGrupal(T resultado);
    }

    void onGuardarAsistenciaLista(List<AsistenciaUi> asistenciaUiList, ObjectCallbackSuccess<Boolean> callbackSuccess);

    void onGuardarAsistenciaListaHoraFin(List<Asistencia> asistenciaList, ObjectCallbackSuccess<Boolean> callbackSuccess);

    void onObtenerAsistenciaLista(String fecha, ObjectCallbackSuccess<List<Asistencia>> listObjectCallbackSuccess);

    void onValidarFechaRegistroAsistencia(String fecha, ObjectCallbackSuccess<Boolean> callbackSuccess);

    void onObtenerAlumnosLista(CursoUi cursoUi , ObjectCallbackSuccess<List<Alumnos>> listObjectCallbackSuccess);

    void onObtenerInformacionAlumnos(List<Alumnos> alumnosList, ObjectCallbackSuccess<Alumnos> listObjectCallbackSuccess);
}
