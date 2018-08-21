package com.systemvv.grupo.asitenciaapp.padre.cursoHijos.dataSource.remote;

import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.dataSource.CursoHijosDataSource;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;

import java.util.List;

public class CursoHijosRemote implements CursoHijosDataSource {

    private FireStore fireStore;

    public CursoHijosRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }

    @Override
    public void onObtenerListaCursosHijos(final Hijos hijos,final onCallBackResultado<List<Cursos>> listonCallBackResultado) {
        fireStore.onObtenerListaCursosHijos(hijos, new FireCallback<List<Cursos>>() {
            @Override
            public void onSuccess(List<Cursos> sucess) {
                listonCallBackResultado.onResult(sucess);
            }
        });
    }

}
