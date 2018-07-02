package com.systemvv.grupo.asitenciaapp.asistencia;

import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaCelda;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaColumnaCabecera;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaFilaCabecera;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import java.util.List;

public interface AsistenciaView extends BaseActivityView<AsistenciaPresenter> {

    void mostrarInformacionBasica(CursoUi cursoUi);


    void mostrarListaTablas(List<AsistenciaColumnaCabecera> columnaCabeceraList, List<AsistenciaFilaCabecera> filaCabeceraList, List<List<AsistenciaCelda>> bodyList);

    void actualizarCeldas(AsistenciaUi asistenciaUi);
}
