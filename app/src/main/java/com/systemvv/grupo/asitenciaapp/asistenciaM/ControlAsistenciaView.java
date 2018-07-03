package com.systemvv.grupo.asitenciaapp.asistenciaM;

import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaCeldas;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaColumna;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaFilas;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import java.util.List;

public interface ControlAsistenciaView extends BaseActivityView<ControlAsistenciaPresenter> {

    void mostrarInformacionBasica(CursoUi cursoUi);

    void mostrarListaTablas(List<AsistenciaColumna> columnHeaderList, List<AsistenciaFilas> rowHeaderList, List<List<AsistenciaCeldas>> cellsList);
}
