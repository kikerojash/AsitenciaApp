package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.dataSource.remote;

import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.dataSource.ReporteIncidenciaDataSource;

import java.util.List;

public class ReporteIncidenciaRemote implements ReporteIncidenciaDataSource {

    private FireStore fireStore;

    public ReporteIncidenciaRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }

    @Override
    public void onMostrarListaReporteIncidencia(Cursos cursos, final onCallBackResultado<List<Incidencias>> listonCallBackResultado) {
        fireStore.onMostrarListaIncidencia(cursos, new FireCallback<List<Incidencias>>() {
            @Override
            public void onSuccess(List<Incidencias> sucess) {
                listonCallBackResultado.onResultado(sucess);
            }
        });
    }

    @Override
    public void onEliminarIncidencia(Incidencias incidencias, final onCallBackResultado<Boolean> booleanonCallBackResultado) {
        fireStore.onEliminarIncidencia(incidencias, new FireCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean sucess) {
                booleanonCallBackResultado.onResultado(sucess);
            }
        });
    }


}
