package com.systemvv.grupo.asitenciaapp.cursos;

import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import java.util.List;

public interface CursoView extends BaseActivityView<CursoPresenter> {
    void mostrarListaCursos(List<CursoUi> cursoUiList);
}
