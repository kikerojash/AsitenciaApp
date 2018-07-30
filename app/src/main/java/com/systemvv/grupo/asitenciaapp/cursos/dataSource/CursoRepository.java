package com.systemvv.grupo.asitenciaapp.cursos.dataSource;

import com.systemvv.grupo.asitenciaapp.cursos.dataSource.remote.CursoRemote;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;

import java.util.List;

public class CursoRepository implements CursoDataSource {

    private CursoRemote cursoRemote;

    public CursoRepository(CursoRemote cursoRemote) {
        this.cursoRemote = cursoRemote;
    }

    @Override
    public void onMostrarListaCurso(SeccionUi seccionUi, CallBackResultado<List<CursoUi>> listCallBackResultado) {
        cursoRemote.onMostrarListaCurso(seccionUi, listCallBackResultado);
    }
}
