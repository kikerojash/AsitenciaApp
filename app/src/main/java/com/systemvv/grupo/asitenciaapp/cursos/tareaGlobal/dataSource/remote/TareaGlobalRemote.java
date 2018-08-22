package com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.dataSource.remote;

import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.dataSource.TareaGlobalDataSource;

import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.entidad.TareasGlobales;
import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TareaGlobalRemote implements TareaGlobalDataSource {

    private FireStore fireStore;

    public TareaGlobalRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }

    @Override
    public void onObtenerListaAlumnos(CursoUi cursoUi, final CallBackResultad<List<TareasGlobales>> callBackResultad) {
        fireStore.onObtenerListaAlumnosTareasGlobales(cursoUi, new FireCallback<List<TareasGlobales>>() {
            @Override
            public void onSuccess(List<TareasGlobales> sucess) {
                callBackResultad.onCallResultado(sucess);
            }
        });
    }

    @Override
    public void onGuardarTareasGlobales(List<TareasGlobales> tareasGlobalesList, String descripcionTareaGlobal, final CallBackResultad<Boolean> booleanCallBackResultad) {
        fireStore.guardarListaAsistenciaAlumnosTareaGlobales(tareasGlobalesListGuardar(tareasGlobalesList, descripcionTareaGlobal), new FireCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean sucess) {
                booleanCallBackResultad.onCallResultado(sucess);
            }
        });
    }

    private List<TareasGlobales> tareasGlobalesListGuardar(List<TareasGlobales> tareasGlobalesList, String descripcionTareaGlobal) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        for (TareasGlobales tareasGlobales : tareasGlobalesList) {
            tareasGlobales.setDescripcionTarea(descripcionTareaGlobal);
            tareasGlobales.setFecha(date);
            tareasGlobales.setTimeStamp(new Date().getTime());
        }
        return tareasGlobalesList;
    }
}
