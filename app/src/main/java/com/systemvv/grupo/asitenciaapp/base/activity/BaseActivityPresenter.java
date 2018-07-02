package com.systemvv.grupo.asitenciaapp.base.activity;

import android.os.Bundle;

import com.systemvv.grupo.asitenciaapp.base.BasePresenter;

public interface BaseActivityPresenter<T extends BaseActivityView> extends BasePresenter<T> {
    void setExtras(Bundle extras);
}
