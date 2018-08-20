package com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.remote;

import android.util.Log;

import com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.IncidenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.entidadui.IncidenciaUi;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Incidencia;

public class IncidenciaRemote implements IncidenciaDataSource {
    public static final String TAG = IncidenciaRemote.class.getSimpleName();

    private FireStore fireStore;

    public IncidenciaRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }

    @Override
    public void onGuardarIncidencia(IncidenciaUi incidenciaUi, final onCallBackResult<Boolean> callBackResult) {

        fireStore.registrarIncidencia(convert(incidenciaUi), new FireCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean sucess) {
                callBackResult.onResponse(sucess);
            }
        });
    }

    @Override
    public void onObtenerAlumno(String keyAlumno, final onCallBackResult<Alumnos> alumnosonCallBackResult) {
        fireStore.onObtenerAlumno(keyAlumno, new FireCallback<Alumnos>() {
            @Override
            public void onSuccess(Alumnos sucess) {
                alumnosonCallBackResult.onResponse(sucess);
            }
        });
    }

    private Incidencia convert(IncidenciaUi incidenciaUi) {
        Incidencia incidencia = new Incidencia();
        Log.d(TAG, "incidenciaUi " + incidenciaUi.getNivelIncidencia());
        incidencia.setInc_prioridad(incidenciaUi.getNivelIncidencia());
        incidencia.setInc_descripcion(incidenciaUi.getMensajeIncidencia());
        incidencia.setInc_fecha(incidenciaUi.getFechaIncidencia());
        incidencia.setAlu_id_alumno(incidenciaUi.getAlu_id_alumno());
        incidencia.setTimeStamp(incidenciaUi.getTimeStamp());
        incidencia.setCur_id_curso(incidenciaUi.getCur_id_curso());
        incidencia.setGra_id_grado(incidenciaUi.getGra_id_grado());
        incidencia.setIns_id_institucion(incidenciaUi.getIns_id_institucion());
        incidencia.setPrd_id_periodo(incidenciaUi.getPrd_id_periodo());
        incidencia.setSec_id_seccion(incidenciaUi.getSec_id_seccion());
        incidencia.setEstadoActivo(true);
        return incidencia;
    }
}
