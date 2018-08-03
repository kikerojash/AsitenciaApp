package com.systemvv.grupo.asitenciaapp.login.dataSource;

import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.login.dataSource.remote.LoginRemote;

public class LoginRepository implements LoginDataSource {

    private LoginRemote remote;

    public LoginRepository(LoginRemote remote) {
        this.remote = remote;
    }

    @Override
    public void onValidatePeriodo(onCallBackResultado<String> stringonCallBackResultado) {
        remote.onValidatePeriodo(stringonCallBackResultado);
    }

    @Override
    public void onValidarTipoUsuario(String tipoUsuario,  onCallBackResultado<UsuarioUi> usuarioUionCallBackResultado) {
        remote.onValidarTipoUsuario(tipoUsuario, usuarioUionCallBackResultado);
    }
}
