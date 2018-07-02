package com.systemvv.grupo.asitenciaapp.base;

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
