package com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.remote;

import com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.IncidenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.entidadui.IncidenciaUi;
import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Incidencia;

public class IncidenciaRemote implements IncidenciaDataSource {

    private FireStore fireStore;

    public IncidenciaRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }

    @Override
    public void onGuardarIncidencia(IncidenciaUi incidenciaUi, onCallBackResult<Boolean> callBackResult) {
        fireStore.registrarIncidencia(convert(incidenciaUi), new FireCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean sucess) {

            }
        });
    }

    private Incidencia convert(IncidenciaUi incidenciaUi) {
        Incidencia incidencia = new Incidencia();
        incidencia.setInc_prioridad(incidenciaUi.getNivelIncidencia());
        incidencia.setInc_descripcion(incidenciaUi.getMensajeIncidencia());
        incidencia.setInc_fecha(incidenciaUi.getFechaIncidencia());
        incidencia.setAlu_id_alumno(incidenciaUi.getAlu_id_alumno());
        incidencia.setTimeStamp(incidenciaUi.getTimeStamp());
        return incidencia;
    }
}
