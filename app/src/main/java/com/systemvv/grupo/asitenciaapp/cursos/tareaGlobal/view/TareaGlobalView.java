package com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.view;


import com.systemvv.grupo.asitenciaapp.base.BaseView;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.TareaGlobalPresenter;

public interface TareaGlobalView extends BaseView<TareaGlobalPresenter> {

    void mostrarMensaje(String mensaje);
}
