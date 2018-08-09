package com.systemvv.grupo.asitenciaapp.padre.cursoHijos.dataSource;



import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;

import java.util.List;

public interface CursoHijosDataSource {

    interface onCallBackResultado<T> {
        void onResult(T resultado);
    }

    void onObtenerListaCursosHijos(Hijos hijos, onCallBackResultado<List<Cursos>> listonCallBackResultado);
}
