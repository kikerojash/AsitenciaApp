package com.systemvv.grupo.asitenciaapp.login;

import android.content.res.Resources;
import android.util.Log;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenterImpl;
import com.systemvv.grupo.asitenciaapp.utils.Utils;

public class LoginPresenterImpl extends BaseActivityPresenterImpl<LoginView> implements LoginPresenter {

    public static final String TAG = LoginPresenterImpl.class.getSimpleName();

    public LoginPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
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

        initLoginActivity(usuario, clave);


    }

    private void initLoginActivity(String usuario, String clave) {
        /*Profesor*/
        if (usuario.equals("juanperez@gmail.com") && clave.equals("123")) {
            if (view!=null)view.initSeleccionarInstituto(usuario,clave);
        } else if (usuario.equals("jorgefernandez@gmail.com") && clave.equals("123")) {/*Padre*/
           // if (view!=null)view.initInstituto(usuario,clave);
        }
    }


}
