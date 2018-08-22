package com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.dataSource;

import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.entidad.TareasGlobales;

import java.util.List;

public interface TareaGlobalDataSource {

    interface CallBackResultad<T> {
        void onCallResultado(T resultado);
    }

    void onObtenerListaAlumnos(CursoUi cursoUi, CallBackResultad<List<TareasGlobales>> callBackResultad);

    void onGuardarTareasGlobales(List<TareasGlobales> tareasGlobalesList,String descripcionTareaGlobal, CallBackResultad<Boolean> booleanCallBackResultad);
}
