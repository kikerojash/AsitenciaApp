package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion;

import com.systemvv.grupo.asitenciaapp.base.BaseView;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import java.util.List;

public interface SeccionView extends BaseView<SeccionPresenter> {

    void initVistas(InstitutoUi institutoUi);

    void mostrarLista(List<SeccionUi> seccionUiList);
}
