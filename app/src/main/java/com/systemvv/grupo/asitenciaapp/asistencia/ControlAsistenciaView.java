package com.systemvv.grupo.asitenciaapp.asistencia;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.ColumnaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.FilaCabeceraAsistencia;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import java.util.List;

public interface ControlAsistenciaView extends BaseActivityView<ControlAsistenciaPresenter> {

    void mostrarInformacionBasica(CursoUi cursoUi);

    void mostrarListaTablas(List<ColumnaCabeceraAsistencia> columnHeaderList, List<FilaCabeceraAsistencia> rowHeaderList, List<List<CeldasAsistencia>> cellsList);

    void actualizarDatosCambiadosTabla();

    void mostrarMensaje(String mensaje);

    void mostrarInformacionSnackBar(String mensaje, int tipoRegistroResultado);

}
