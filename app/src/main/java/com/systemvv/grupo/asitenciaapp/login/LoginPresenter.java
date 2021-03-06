package com.systemvv.grupo.asitenciaapp.login;

import android.app.ProgressDialog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenter;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;

public interface LoginPresenter extends BaseActivityPresenter<LoginView> {

    void onIniciarSesion(String usuario, String clave);

    void onValidarAutenticacionInicio(String usuario);

    void onValidarAutenticacion(FirebaseAuth firebaseUser, ProgressDialog progressDialog);

    void onClickRolPadre(UsuarioUi usuarioUi, String keyperiodo);

    void onClickRolDocente(UsuarioUi usuarioUi, String keyperiodo);
}
