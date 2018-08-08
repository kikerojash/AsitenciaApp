package com.systemvv.grupo.asitenciaapp.login;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.login.useCase.ValidarPeriodo;
import com.systemvv.grupo.asitenciaapp.login.useCase.ValidarRolUsuario;
import com.systemvv.grupo.asitenciaapp.utils.Utils;

public class LoginPresenterImpl extends BaseActivityPresenterImpl<LoginView> implements LoginPresenter {

    public static final String TAG = LoginPresenterImpl.class.getSimpleName();

    private ValidarPeriodo validarPeriodo;
    private ValidarRolUsuario validarRolUsuario;

    public LoginPresenterImpl(UseCaseHandler handler, Resources res, ValidarPeriodo validarPeriodo, ValidarRolUsuario validarRolUsuario) {
        super(handler, res);
        this.validarPeriodo = validarPeriodo;
        this.validarRolUsuario = validarRolUsuario;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }


    @Override
    public void onIniciarSesion(String usuario, String clave) {
        if (!Utils.isValideString(usuario) && !Utils.isValideString(clave)) {
            Log.d(TAG, "usuarioclave");
            if (view != null)
                view.mostrarCamposIncompleto(res.getString(R.string.campos_incompleto));
            return;
        } else {
            if (view != null)
                view.nulearCamposIncompleto();
        }
        if (!Utils.isValideString(clave)) {
            Log.d(TAG, "clave");
            if (view != null) view.mostrarClaveIncompleto(res.getString(R.string.clave_incompleto));
            return;
        } else {
            if (view != null)
                view.nulearClaveIncompleto();
        }
        if (!Utils.isValideString(usuario)) {
            Log.d(TAG, "usuario");
            if (view != null)
                view.mostrarUsuarioIncompleto(res.getString(R.string.usuario_incompleto));
            return;
        } else {
            if (view != null)
                view.nulearUsuarioIncompleto();
        }

        initAutenticacion(usuario, clave);
        //initLoginActivity(usuario, clave);


    }

    @Override
    public void onCreate() {
        super.onCreate();
        //    initValidarPeriodo(usuario);
    }

    /*
     *
     * Validar Periodo ---- Validar TipoUsuario, si el periodo esta Activo
     * */
    @Override
    public void onValidarAutenticacionInicio(String usuario) {
        initValidarPeriodo(usuario);
        // initRolesAutenticado(usuario);
    }

    @Override
    public void onValidarAutenticacion(FirebaseAuth firebaseAuth, ProgressDialog progressDialog) {
        if (firebaseAuth.getCurrentUser() != null) {
            String usuario = firebaseAuth.getCurrentUser().getEmail();
            initValidarPeriodo(usuario);
            //progressDialog.dismiss();
        }
    }



    private void initValidarPeriodo(final String usuario) {
        handler.execute(validarPeriodo, new ValidarPeriodo.RequestValues(),
                new UseCase.UseCaseCallback<ValidarPeriodo.ResponseValue>() {
                    @Override
                    public void onSuccess(ValidarPeriodo.ResponseValue response) {
                        //Entrar al nodo Usuario y validar TipoUsuario
                        if (response.getKeyPeriodo() == null) {
                            Log.d(TAG, "Otro tipo de Peridoo2");
                            // if (view != null) view.initSeleccionarInstituto(usuario, "wsp");
                        } else if (response.getKeyPeriodo() != null) {
                            String keyPeriodo = response.getKeyPeriodo();
                            initValidarTipoUsuario(usuario, keyPeriodo);
                            Log.d(TAG, " keyPeriodo "+keyPeriodo);
                            // if (view != null) view.initSeleccionarInstituto(usuario, keyPeriodo);
                        } else {
                            Log.d(TAG, "Otro tipo de Peridoo");
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initValidarTipoUsuario(final String usuario,final String keyPeriodo) {
        handler.execute(validarRolUsuario, new ValidarRolUsuario.RequestValues(usuario),
                new UseCase.UseCaseCallback<ValidarRolUsuario.ResponseValue>() {
                    @Override
                    public void onSuccess(ValidarRolUsuario.ResponseValue response) {
                        if (response.getUsuarioUi().getTip_usuario_nombre().equals("PROFESOR")) {
                            if (view != null) view.initSeleccionarInstituto(response.getUsuarioUi(), keyPeriodo);
                            Log.d(TAG, "PROFESOR");
                        } else if (response.getUsuarioUi().getTip_usuario_nombre().equals("APODERADO")) {
                            Log.d(TAG, "APODERADO");
                        } else if (response.getUsuarioUi().getTip_usuario_nombre().equals("AMBOS")) {
                            Log.d(TAG, "AMBOS");
                        } else {
                            Log.d(TAG, "wsP");
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    private void initAutenticacion(String usuario, String clave) {
        // if (view!=null)view.initVistaPadre(usuario,clave);
        if (view != null) view.initAutenticacion(usuario, clave);
    }

//    private void initLoginActivity(String usuario, String clave) {
//        /*Profesor*/
//        if (usuario.equals("juanperez@gmail.com") && clave.equals("123")) {
//            if (view != null) view.initSeleccionarInstituto(usuario, clave);
//        } else if (usuario.equals("jorgefernandez@gmail.com") && clave.equals("123")) {/*Padre*/
//            if (view != null) view.initVistaPadre(usuario, clave);
//        }
//    }


}
