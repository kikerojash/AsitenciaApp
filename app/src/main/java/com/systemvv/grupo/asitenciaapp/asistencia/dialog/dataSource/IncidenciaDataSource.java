package com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource;

import com.systemvv.grupo.asitenciaapp.asistencia.dialog.entidadui.IncidenciaUi;

public interface IncidenciaDataSource {

    interface onCallBackResult<T> {
        void onResponse(T response);
    }
    void onGuardarIncidencia(IncidenciaUi incidenciaUi, onCallBackResult<Boolean> callBackResult);
}
