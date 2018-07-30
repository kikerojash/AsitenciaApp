package com.systemvv.grupo.asitenciaapp.cursos.dataSource;

import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;

import java.util.List;

public interface CursoDataSource {

    interface CallBackResultado<T>{
        void onCalBackResultado(T resultado);
    }

    void onMostrarListaCurso(SeccionUi seccionUi, CallBackResultado<List<CursoUi> > listCallBackResultado);
}
