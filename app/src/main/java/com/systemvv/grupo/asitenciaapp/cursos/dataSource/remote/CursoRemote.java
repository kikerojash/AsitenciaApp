package com.systemvv.grupo.asitenciaapp.cursos.dataSource.remote;

import com.systemvv.grupo.asitenciaapp.cursos.dataSource.CursoDataSource;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;

import java.util.List;

public class CursoRemote implements CursoDataSource {

    private FireStore fireStore;

    public CursoRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }


    @Override
    public void onMostrarListaCurso(SeccionUi seccionUi,final CallBackResultado<List<CursoUi>> listCallBackResultado) {
        fireStore.onObtenerListaCursos(seccionUi, new FireCallback<List<CursoUi>>() {
            @Override
            public void onSuccess(List<CursoUi> sucess) {
                listCallBackResultado.onCalBackResultado(sucess);
            }
        });
    }
}
