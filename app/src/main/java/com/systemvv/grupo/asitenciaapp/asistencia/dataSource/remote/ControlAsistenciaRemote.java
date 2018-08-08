package com.systemvv.grupo.asitenciaapp.asistencia.dataSource.remote;


import android.util.Log;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.asistencia.fireListener.FireCallBackList;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Asistencia;

import java.util.ArrayList;
import java.util.List;


public class ControlAsistenciaRemote implements ControlAsistenciaDataSource {

    public static final String TAG = ControlAsistenciaRemote.class.getSimpleName();

    private FireStore fireStore;

    public ControlAsistenciaRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }


    @Override
    public void onGuardarAsistenciaLista(List<com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia> asistenciaUiList,CursoUi cursoUi, final ObjectCallbackSuccess<Boolean> callbackSuccess) {
        fireStore.guardarListaAsistenciaAlumnos(convertAsistenciaLista(asistenciaUiList,cursoUi), new FireCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean sucess) {
                if (sucess) {
                    callbackSuccess.guardarAsistenciaGrupal(true);
                }
            }
        });
    }

    @Override
    public void onGuardarAsistenciaListaHoraFin(List<String> asistenciaList, final ObjectCallbackSuccess<Boolean> callbackSuccess) {
        fireStore.guardarListaAsistenciaHoraFin(asistenciaList, new FireCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean sucess) {
                if (sucess) {
                    callbackSuccess.guardarAsistenciaGrupal(true);
                } else {
                    callbackSuccess.guardarAsistenciaGrupal(false);
                }
            }
        });
    }




    @Override
    public void onValidarFechaRegistroAsistencia(String fecha,CursoUi cursoUi,final ObjectCallbackSuccessAsistencia<Boolean, List<String>> callbackSuccess) {
        fireStore.validarFechaRegistroAsistencia(fecha,cursoUi, new FireCallBackList<Boolean>() {
            @Override
            public void onSuccess(Boolean sucess, List<String> kList, int tipoValidacion) {
                callbackSuccess.guardarAsistenciaGrupal(sucess, kList, tipoValidacion);
            }
        });
    }




    @Override
    public void onObtenerAlumnosLista(CursoUi cursoUi, final ObjectCallbackSuccess<List<Alumnos>> listObjectCallbackSuccess) {
        fireStore.onObtenerListaAlumnos(cursoUi, new FireCallback<List<Alumnos>>() {
            @Override
            public void onSuccess(List<Alumnos> alumnosList) {
                Log.d(TAG, "onObtenerAlumnosLista : " + alumnosList.size());
                listObjectCallbackSuccess.guardarAsistenciaGrupal(alumnosList);
            }
        });
    }


    private List<Asistencia> convertAsistenciaLista(List<com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia> asistenciaUiList,CursoUi cursoUi) {
        List<Asistencia> asistenciasList = new ArrayList<>();
        String grado = String.valueOf(cursoUi.getGradoSelected());
        String seccion = cursoUi.getSeccionSelected();
        String keyPeriodo = cursoUi.getInstitutoUi().getKeyPeriodo();
        String keyCurso = cursoUi.getKeyCurso();
        String keyInstituto = cursoUi.getInstitutoUi().getKeyInstituto();
        for (com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia asistenciaUi : asistenciaUiList) {
            Asistencia asistencia = new Asistencia();
            asistencia.setAlu_id_alumno(asistenciaUi.getAlumnosUi().getKeyAlumno());
            asistencia.setAsi_fecha(asistenciaUi.getFecha());
            asistencia.setAsi_hora_inicio(asistenciaUi.getHoraInicioCurso());
            asistencia.setAsi_tipo_asistencia(asistenciaUi.getTipoAsistencia());
            asistencia.setCur_id_curso(keyCurso);
            asistencia.setGra_id_grado(grado);
            asistencia.setIns_id_institucion(keyInstituto);
            asistencia.setPrd_id_periodo(keyPeriodo);
            asistencia.setSec_id_seccion(seccion);
            asistenciasList.add(asistencia);
        }
        return asistenciasList;
    }


}
