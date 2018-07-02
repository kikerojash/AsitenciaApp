package com.systemvv.grupo.asitenciaapp.base.activity;

import com.systemvv.grupo.asitenciaapp.base.BasePresenter;
import com.systemvv.grupo.asitenciaapp.base.BaseView;

public interface BaseActivityView<T extends BasePresenter> extends BaseView<T> {

    void mostrarProgressBar();

    void ocultarProgressBar();



}
