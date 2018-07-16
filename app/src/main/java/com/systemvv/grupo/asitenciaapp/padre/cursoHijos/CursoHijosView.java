package com.systemvv.grupo.asitenciaapp.padre.cursoHijos;

import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;

import java.util.List;

public interface CursoHijosView extends BaseActivityView<CursoHijosPresenter> {

    void mostrarListaCursos(List<Cursos> cursosList);
}
