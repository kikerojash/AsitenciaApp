package com.systemvv.grupo.asitenciaapp.login.dataSource.remote;

import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.login.dataSource.LoginDataSource;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;

public class LoginRemote implements LoginDataSource {

    private FireStore fireStore;

    public LoginRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }


    @Override
    public void onValidatePeriodo(final onCallBackResultado<String> stringonCallBackResultado) {
        fireStore.onValidarPeriodo(new FireCallback<String>() {
            @Override
            public void onSuccess(String sucess) {
                stringonCallBackResultado.onCallBackResultado(sucess);
            }
        });
    }

    @Override
    public void onValidarTipoUsuario(String tipoUsuario, final onCallBackResultado<UsuarioUi> usuarioUionCallBackResultado) {
        fireStore.onValidarTipoUsuario(tipoUsuario, new FireCallback<UsuarioUi>() {
            @Override
            public void onSuccess(UsuarioUi sucess) {
                usuarioUionCallBackResultado.onCallBackResultado(sucess);
            }
        });
    }
}
