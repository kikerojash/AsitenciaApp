package com.systemvv.grupo.asitenciaapp.asistencia.dataSource;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.remote.ControlAsistenciaRemote;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import java.util.List;

public class ControlAsistenciaRepository implements ControlAsistenciaDataSource {

    public static final String TAG = ControlAsistenciaRepository.class.getSimpleName();

    private ControlAsistenciaRemote asistenciaRemote;

    public ControlAsistenciaRepository(ControlAsistenciaRemote asistenciaRemote) {
        this.asistenciaRemote = asistenciaRemote;
    }


    @Override
    public void onGuardarAsistenciaLista(List<com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia> asistenciaUiList,CursoUi cursoUi, ObjectCallbackSuccess<Boolean> callbackSuccess) {
        asistenciaRemote.onGuardarAsistenciaLista(asistenciaUiList,cursoUi, callbackSuccess);
    }

    @Override
    public void onGuardarAsistenciaListaHoraFin(List<String> asistenciaList, ObjectCallbackSuccess<Boolean> callbackSuccess) {
        asistenciaRemote.onGuardarAsistenciaListaHoraFin(asistenciaList, callbackSuccess);
    }


    @Override
    public void onValidarFechaRegistroAsistencia(String fecha,CursoUi cursoUi, ObjectCallbackSuccessAsistencia<Boolean, List<String>> callbackSuccess) {
        asistenciaRemote.onValidarFechaRegistroAsistencia(fecha,cursoUi, callbackSuccess);
    }


    @Override
    public void onObtenerAlumnosLista(CursoUi cursoUi, final ObjectCallbackSuccess<List<Alumnos>> listObjectCallbackSuccess) {
        asistenciaRemote.onObtenerAlumnosLista(cursoUi, listObjectCallbackSuccess);
    }

    @Override
    public void onObtenerDatosDocente(String keyDocente, ObjectCallbackSuccessString<String,String> stringObjectCallbackSuccess) {
        asistenciaRemote.onObtenerDatosDocente(keyDocente,stringObjectCallbackSuccess);
    }


}
