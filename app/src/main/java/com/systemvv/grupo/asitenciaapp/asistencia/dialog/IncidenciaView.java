package com.systemvv.grupo.asitenciaapp.asistencia.dialog;

import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.base.BaseView;


public interface IncidenciaView extends BaseView<IncidenciaPresenter> {

    void mostrarMensaje(String mensaje);

    void initVistas(Alumnos alumnosUi);
}
