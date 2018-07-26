package com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource;

import com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.remote.IncidenciaRemote;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.entidadui.IncidenciaUi;

public class IncidenciaRepository implements IncidenciaDataSource {

    private IncidenciaRemote remote;

    public IncidenciaRepository(IncidenciaRemote remote) {
        this.remote = remote;
    }

    @Override
    public void onGuardarIncidencia(IncidenciaUi incidenciaUi, onCallBackResult<Boolean> callBackResult) {
        remote.onGuardarIncidencia(incidenciaUi, callBackResult);
    }
}
