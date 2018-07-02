package com.systemvv.grupo.asitenciaapp.seleccionarInstituto;

import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import java.util.List;

public interface InstitutoView extends BaseActivityView<InstitutoPresenter>{

    void mostrarListaInstitutos(List<InstitutoUi> institutoUiList);
}
