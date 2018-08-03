package com.systemvv.grupo.asitenciaapp.login.dataSource;

import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;

public interface LoginDataSource {


    interface onCallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onValidatePeriodo(onCallBackResultado<String> stringonCallBackResultado);

    void onValidarTipoUsuario(String tipoUsuario, onCallBackResultado<UsuarioUi> usuarioUionCallBackResultado);
}
