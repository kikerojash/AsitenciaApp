package com.systemvv.grupo.asitenciaapp.login;

import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenter;

public interface LoginPresenter extends BaseActivityPresenter<LoginView>{

    void onIniciarSesion(String usuario,String clave);
}
