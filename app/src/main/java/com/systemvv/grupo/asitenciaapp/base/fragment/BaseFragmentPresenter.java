package com.systemvv.grupo.asitenciaapp.base.fragment;

import android.os.Bundle;

import com.systemvv.grupo.asitenciaapp.base.BasePresenter;
import com.systemvv.grupo.asitenciaapp.base.BaseView;


public interface BaseFragmentPresenter<T extends BaseView> extends BasePresenter<T> {
    void onCreateView();
    void onViewCreated();
    void onActivityCreated();
    void onDestroyView();
    void onDetach();
    void setExtras(Bundle extras);
}
