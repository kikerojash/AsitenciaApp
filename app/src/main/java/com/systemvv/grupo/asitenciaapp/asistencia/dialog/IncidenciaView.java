package com.systemvv.grupo.asitenciaapp.asistencia.dialog;

import com.systemvv.grupo.asitenciaapp.base.BaseView;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;


public interface IncidenciaView extends BaseView<IncidenciaPresenter> {

    void mostrarMensaje(String mensaje);

    void initVistas(AlumnosUi alumnosUi);
}
