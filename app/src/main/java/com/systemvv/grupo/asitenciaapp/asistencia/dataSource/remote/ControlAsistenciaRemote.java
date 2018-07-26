package com.systemvv.grupo.asitenciaapp.asistencia.dataSource.remote;



import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
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
    public void onGuardarAsistenciaLista(List<AsistenciaUi> asistenciaUiList,final ObjectCallbackSuccess<Boolean> callbackSuccess) {
        fireStore.guardarListaAsistenciaAlumnos(convertAsistenciaLista(asistenciaUiList), new FireCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean sucess) {
                if(sucess){callbackSuccess.guardarAsistenciaGrupal(true);}
                //booleanObjectCallbackSuccess.registroCorrecto(sucess);
            }
        });
    }

    @Override
    public void onGuardarAsistenciaListaHoraFin(List<Asistencia> asistenciaList, final ObjectCallbackSuccess<Boolean> callbackSuccess) {
        fireStore.guardarListaAsistenciaHoraFin(asistenciaList, new FireCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean sucess) {
                if(sucess){
                    callbackSuccess.guardarAsistenciaGrupal(true);
                }else{
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
                if(sucess){
                    callbackSuccess.guardarAsistenciaGrupal(true);
                }else{
                    callbackSuccess.guardarAsistenciaGrupal(false);
                }
            }
        });
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
