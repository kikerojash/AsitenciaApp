package com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.dataSource;

import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.dataSource.remote.TareaGlobalRemote;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.entidad.TareasGlobales;

import java.util.List;

public class TareaGlobalRepository implements TareaGlobalDataSource {

    private TareaGlobalRemote tareaGlobalRemote;

    public TareaGlobalRepository(TareaGlobalRemote tareaGlobalRemote) {
        this.tareaGlobalRemote = tareaGlobalRemote;
    }

    @Override
    public void onObtenerListaAlumnos(CursoUi cursoUi, CallBackResultad<List<TareasGlobales>> callBackResultad) {
        tareaGlobalRemote.onObtenerListaAlumnos(cursoUi, callBackResultad);
    }

    @Override
    public void onGuardarTareasGlobales(List<TareasGlobales> tareasGlobalesList,String descripcionTareaGlobal, CallBackResultad<Boolean> booleanCallBackResultad) {
        tareaGlobalRemote.onGuardarTareasGlobales(tareasGlobalesList,descripcionTareaGlobal, booleanCallBackResultad);
    }
}
