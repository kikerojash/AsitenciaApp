package com.systemvv.grupo.asitenciaapp.asistencia.dataSource.remote;


import android.util.Log;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.MotivoAsistencia;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;
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
    public void onGuardarAsistenciaLista(List<AsistenciaUi> asistenciaUiList, final ObjectCallbackSuccess<Boolean> callbackSuccess) {
        fireStore.guardarListaAsistenciaAlumnos(convertAsistenciaLista(asistenciaUiList), new FireCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean sucess) {
                if (sucess) {
                    callbackSuccess.guardarAsistenciaGrupal(true);
                }
                //booleanObjectCallbackSuccess.registroCorrecto(sucess);
            }
        });
    }

    @Override
    public void onGuardarAsistenciaListaHoraFin(List<Asistencia> asistenciaList, final ObjectCallbackSuccess<Boolean> callbackSuccess) {
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
    public void onObtenerAsistenciaLista(String fecha, final ObjectCallbackSuccess<List<Asistencia>> listObjectCallbackSuccess) {
        fireStore.onObtenerAsistenciaLista(fecha, new FireCallback<List<Asistencia>>() {
            @Override
            public void onSuccess(List<Asistencia> sucess) {
                listObjectCallbackSuccess.guardarAsistenciaGrupal(sucess);
            }
        });
    }

    @Override
    public void onValidarFechaRegistroAsistencia(String fecha, final ObjectCallbackSuccess<Boolean> callbackSuccess) {
        fireStore.validarFechaRegistroAsistencia(fecha, new FireCallback<Boolean>() {
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
    public void onObtenerAlumnosLista(CursoUi cursoUi, final ObjectCallbackSuccess<List<Alumnos>> listObjectCallbackSuccess) {
        fireStore.onObtenerListaAlumnos(cursoUi, new FireCallback<List<Alumnos>>() {
            @Override
            public void onSuccess(List<Alumnos> alumnosList) {
                // actualizandoListaAlumnos(alumnosList);
                Log.d(TAG, "onObtenerAlumnosLista : " + alumnosList.size());
                listObjectCallbackSuccess.guardarAsistenciaGrupal(alumnosList);
            }
        });
    }

    private void actualizandoListaAlumnos(List<Alumnos> alumnosList) {
        for (Alumnos alumnos : alumnosList) {
            Log.d(TAG, "actualizandoListaAlumnos :" + alumnos.getAlumnoMotivoAsistenciaColumna().name());

            switch (alumnos.getAlumnoMotivoAsistenciaColumna()) {
                /*case COLUMNA_PRESENTE:
                    List<MotivoAsistencia> motivoAsistenciaPuntualList = new ArrayList<>();
                    MotivoAsistencia motivosAsistenciaPuntual = new MotivoAsistencia();
                    motivosAsistenciaPuntual.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
                    motivosAsistenciaPuntual.setAsistenciaUiList(asistenciaListPuntual(motivosAsistenciaPuntual, alumnos));
                    motivoAsistenciaPuntualList.add(motivosAsistenciaPuntual);
                    break;
                case COLUMNA_TARDE:
                    List<MotivoAsistencia> motivoAsistenciaTardeList = new ArrayList<>();
                    MotivoAsistencia motivosAsistenciaTardeUi = new MotivoAsistencia();
                    motivosAsistenciaTardeUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE);
                    motivoAsistenciaTardeList.add(motivosAsistenciaTardeUi);
                    break;
                case COLUMNA_FALTO:
                    List<MotivoAsistencia> motivoAsistenciaFaltoList = new ArrayList<>();
                    MotivoAsistencia motivosAsistenciaFaltoUi = new MotivoAsistencia();
                    motivosAsistenciaFaltoUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO);
                    motivoAsistenciaFaltoList.add(motivosAsistenciaFaltoUi);
                    break;
                case COLUMNA_DEFECTO:
                    alumnos.setMotivoAsistenciaList(motivoAsistenciaList(alumnos));
                    break;*/

                case COLUMNA_PRESENTE:

                    /*Motivo*/
                    MotivoAsistencia motivosAsistenciaPuntual = new MotivoAsistencia();
                    motivosAsistenciaPuntual.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
                    motivosAsistenciaPuntual.setJustificacion("PRESENTE");
                    /*Fin Motivo*/

                    com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia asistenciaPuntual = new com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia();
                    asistenciaPuntual.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
                    asistenciaPuntual.setMotivosAsistenciaUi(motivosAsistenciaPuntual);
                    asistenciaPuntual.setPintar(false);
                    asistenciaPuntual.setTipoAsistencia("PUNTUAL");
                    asistenciaPuntual.setAlumnosUi(alumnos);


                    // asistenciaListPuntual(motivosAsistenciaPuntual,alumnos);

//                    MotivoAsistencia motivosAsistenciaPuntual = new MotivoAsistencia();
//                    motivosAsistenciaPuntual.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
//                    motivosAsistenciaPuntual.setJustificacion("PRESENTE");
//                    asistenciaListPuntual(motivosAsistenciaPuntual,alumnos);
//                    alumnos.setMotivoAsistencia(motivosAsistenciaPuntual);
                    break;
                case COLUMNA_TARDE:
                    List<MotivoAsistencia> motivoAsistenciaTardeList = new ArrayList<>();
                    MotivoAsistencia motivosAsistenciaTardeUi = new MotivoAsistencia();
                    motivosAsistenciaTardeUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE);
                    motivoAsistenciaTardeList.add(motivosAsistenciaTardeUi);
                    break;
                case COLUMNA_FALTO:
                    List<MotivoAsistencia> motivoAsistenciaFaltoList = new ArrayList<>();
                    MotivoAsistencia motivosAsistenciaFaltoUi = new MotivoAsistencia();
                    motivosAsistenciaFaltoUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO);
                    motivoAsistenciaFaltoList.add(motivosAsistenciaFaltoUi);
                    break;
                case COLUMNA_DEFECTO:
                    alumnos.setMotivoAsistenciaList(motivoAsistenciaList(alumnos));
                    break;
            }
        }
    }


    private List<MotivoAsistencia> motivoAsistenciaList(Alumnos alumnos) {

        List<MotivoAsistencia> motivoAsistenciaList = new ArrayList<>();

        MotivoAsistencia motivosAsistenciaPuntual = new MotivoAsistencia();
        motivosAsistenciaPuntual.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
        motivosAsistenciaPuntual.setAsistenciaUiList(asistenciaListPuntual(motivosAsistenciaPuntual, alumnos));


        MotivoAsistencia motivosAsistenciaTardeUi = new MotivoAsistencia();
        motivosAsistenciaTardeUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE);
        motivosAsistenciaTardeUi.setAsistenciaUiList(asistenciaListTarde(motivosAsistenciaTardeUi, alumnos));


        MotivoAsistencia motivosAsistenciaFaltoUi = new MotivoAsistencia();
        motivosAsistenciaFaltoUi.setTipoMotivo(MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO);
        motivosAsistenciaFaltoUi.setAsistenciaUiList(asistenciaListFalto(motivosAsistenciaFaltoUi, alumnos));


        motivoAsistenciaList.add(motivosAsistenciaPuntual);
        motivoAsistenciaList.add(motivosAsistenciaTardeUi);
        motivoAsistenciaList.add(motivosAsistenciaFaltoUi);
        return motivoAsistenciaList;

    }

    private List<com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia> asistenciaListPuntual(MotivoAsistencia motivosAsistenciaPuntual, Alumnos alumnos) {
        List<com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia> listAsistencias = new ArrayList<>();
        com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia asistenciaPuntual = new com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia();
        asistenciaPuntual.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_PUNTUAL);
        asistenciaPuntual.setMotivosAsistenciaUi(motivosAsistenciaPuntual);
        asistenciaPuntual.setPintar(false);
        asistenciaPuntual.setTipoAsistencia("PUNTUAL");
        asistenciaPuntual.setAlumnosUi(alumnos);
        listAsistencias.add(asistenciaPuntual);
        alumnos.setAsistencia(asistenciaPuntual);
        motivosAsistenciaPuntual.setAsistenciaUiList(listAsistencias);
        return listAsistencias;
    }

    private List<com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia> asistenciaListTarde(MotivoAsistencia motivosAsistenciaPuntual, Alumnos alumnos) {
        List<com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia> listAsistencias = new ArrayList<>();
        com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia asistenciaPuntual = new com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia();
        asistenciaPuntual.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_TARDE);
        asistenciaPuntual.setMotivosAsistenciaUi(motivosAsistenciaPuntual);
        asistenciaPuntual.setPintar(false);
        asistenciaPuntual.setTipoAsistencia("TARDE");
        asistenciaPuntual.setAlumnosUi(alumnos);
        alumnos.setAsistencia(asistenciaPuntual);
        return listAsistencias;
    }

    private List<com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia> asistenciaListFalto(MotivoAsistencia motivosAsistenciaPuntual, Alumnos alumnos) {
        List<com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia> listAsistencias = new ArrayList<>();
        com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia asistenciaPuntual = new com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia();
        asistenciaPuntual.setJustificacion(MotivosAsistenciaUi.TIPO_ASISTENCIA_FALTO);
        asistenciaPuntual.setMotivosAsistenciaUi(motivosAsistenciaPuntual);
        asistenciaPuntual.setPintar(false);
        asistenciaPuntual.setTipoAsistencia("FALTO");
        asistenciaPuntual.setAlumnosUi(alumnos);
        alumnos.setAsistencia(asistenciaPuntual);
        return listAsistencias;
    }

    private List<Asistencia> convertAsistenciaLista(List<AsistenciaUi> asistenciaUiList) {
        List<Asistencia> asistenciasList = new ArrayList<>();
        for (AsistenciaUi asistenciaUi : asistenciaUiList) {
            Asistencia asistencia = new Asistencia();
            asistencia.setAsi_fecha(asistenciaUi.getFecha());
            asistencia.setAsi_hora_inicio(asistenciaUi.getHoraInicioCurso());
            asistencia.setAsi_tipo_asistencia(asistenciaUi.getTipoAsistencia());
            asistencia.setAlu_id_alumno(asistenciaUi.getAlumnosUi().getNombre());
            asistenciasList.add(asistencia);
        }
        return asistenciasList;
    }


}
