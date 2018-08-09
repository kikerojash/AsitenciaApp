package com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource;

import com.systemvv.grupo.asitenciaapp.asistencia.dialog.entidadui.IncidenciaUi;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;

public interface IncidenciaDataSource {

    interface onCallBackResult<T> {
        void onResponse(T response);
    }
    void onGuardarIncidencia(IncidenciaUi incidenciaUi, onCallBackResult<Boolean> callBackResult);

    void onObtenerAlumno(String keyAlumno, onCallBackResult<Alumnos> alumnosonCallBackResult);
}
