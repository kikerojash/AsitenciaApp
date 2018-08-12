package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.dataSource.remote;

import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.dataSource.ReporteAsistenciaDataSource;

import java.util.List;

public class ReporteAsistenciaRemote implements ReporteAsistenciaDataSource {

    private FireStore fireStore;

    public ReporteAsistenciaRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }

    @Override
    public void onMostrarListaReporteAsistencia(Cursos cursos, final onCallBackResultado<List<Asistencia>> listonCallBackResultado) {
        fireStore.onMostrarListaReporteAsistencia(cursos, new FireCallback<List<Asistencia>>() {
            @Override
            public void onSuccess(List<Asistencia> sucess) {
                listonCallBackResultado.onResultado(sucess);
            }
        });
    }
}
