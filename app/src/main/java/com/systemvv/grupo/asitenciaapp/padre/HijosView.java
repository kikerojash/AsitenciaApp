package com.systemvv.grupo.asitenciaapp.padre;

import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;

import java.util.List;

public interface HijosView extends BaseActivityView<HijosPresenter> {
    void mostrarListaHijos(List<Hijos> hijosList);
}
