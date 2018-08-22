package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.dataSource.remote;

import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Tareas;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.dataSource.ReporteTareasDataSource;

import java.util.List;

public class ReporteTareasRemote implements ReporteTareasDataSource {

    private FireStore fireStore;

    public ReporteTareasRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }

    @Override
    public void onObtenerListaTareas(Cursos cursos, final CallbackResultado<List<Tareas>> listCallbackResultado) {
        fireStore.onMostrarListaTareas(cursos, new FireCallback<List<Tareas>>() {
            @Override
            public void onSuccess(List<Tareas> sucess) {
                listCallbackResultado.onCallBackResultado(sucess);
            }
        });
    }
}
