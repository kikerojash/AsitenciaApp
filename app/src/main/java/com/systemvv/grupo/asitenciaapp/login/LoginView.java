package com.systemvv.grupo.asitenciaapp.login;

import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityView;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;

public interface LoginView extends BaseActivityView<LoginPresenter> {
    void mostrarUsuarioIncompleto(String mensajeError);

    void mostrarClaveIncompleto(String mensajeError);

    void mostrarCamposIncompleto(String mensajeError);

    void nulearCamposIncompleto();

    void nulearUsuarioIncompleto();

    void nulearClaveIncompleto();

    void initSeleccionarInstituto(UsuarioUi usuario, String keyPeriodo);

    void initVistaPadre(String usuarioPadre,String ClavePadre);

    void initAutenticacion(String usuario,String clave);


}
