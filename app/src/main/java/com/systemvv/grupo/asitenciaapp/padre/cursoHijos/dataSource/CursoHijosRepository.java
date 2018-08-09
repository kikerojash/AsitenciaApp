package com.systemvv.grupo.asitenciaapp.padre.cursoHijos.dataSource;

import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.dataSource.remote.CursoHijosRemote;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;

import java.util.List;

public class CursoHijosRepository implements CursoHijosDataSource {

    private CursoHijosRemote cursoHijosRemote;

    public CursoHijosRepository(CursoHijosRemote cursoHijosRemote) {
        this.cursoHijosRemote = cursoHijosRemote;
    }

    @Override
    public void onObtenerListaCursosHijos(Hijos hijos, onCallBackResultado<List<Cursos>> listonCallBackResultado) {
        cursoHijosRemote.onObtenerListaCursosHijos(hijos, listonCallBackResultado);
    }
}
