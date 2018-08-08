package com.systemvv.grupo.asitenciaapp.login;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.base.UseCaseHandler;
import com.systemvv.grupo.asitenciaapp.base.UseCaseThreadPoolScheduler;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivity;
import com.systemvv.grupo.asitenciaapp.conexion.FireAuthConexion;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.login.dataSource.LoginRepository;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.login.dataSource.remote.LoginRemote;
import com.systemvv.grupo.asitenciaapp.login.useCase.ValidarPeriodo;
import com.systemvv.grupo.asitenciaapp.login.useCase.ValidarRolUsuario;
import com.systemvv.grupo.asitenciaapp.padre.HijosActivity;
import com.systemvv.grupo.asitenciaapp.padre.dialogHijos.DialogHijos;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.InstitutoActivity;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.et_usuario)
    EditText editTextUsuario;
    @BindView(R.id.et_password)
    EditText editTextClave;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.desing_usuario)
    TextInputLayout textInputLayoutUsuario;
    @BindView(R.id.desing_password)
    TextInputLayout textInputLayoutClave;

    //progress dialog
    private ProgressDialog progressDialog;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    public static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected LoginPresenter getPresenter() {
        LoginRepository loginRepository = new LoginRepository(new LoginRemote(new FireStore()));
        return new LoginPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new ValidarPeriodo(loginRepository),
                new ValidarRolUsuario(loginRepository));
    }

    @Override
    protected LoginView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //initializing firebase authentication object
        //   firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth = FireAuthConexion.getInstance();
        progressDialog = new ProgressDialog(this);
        editTextUsuario.setText("sumire@gmail.com");
        editTextClave.setText("sumire");

    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @OnClick({R.id.btnLogin, R.id.iv_systemweb, R.id.iv_systemMaps, R.id.iv_systemFacebook, R.id.iv_systemTwitter, R.id.iv_systemGoogle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                String usuario = editTextUsuario.getText().toString();
                String clave = editTextClave.getText().toString();
                presenter.onIniciarSesion(usuario, clave);
                break;
            case R.id.iv_systemweb:
                mostrarSnackbarMensaje("Web");
                break;
            case R.id.iv_systemMaps:
                mostrarSnackbarMensaje("Maps");
                break;
            case R.id.iv_systemFacebook:
                mostrarSnackbarMensaje("Facebook");
                break;
            case R.id.iv_systemTwitter:
                mostrarSnackbarMensaje("Twitter");
                break;
            case R.id.iv_systemGoogle:
                mostrarSnackbarMensaje("Google");
                break;
            default:
                break;
        }
    }

    private void mostrarSnackbarMensaje(String mensaje) {
        Snackbar.make(progressBar, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarUsuarioIncompleto(String mensajeError) {
        textInputLayoutUsuario.setError(mensajeError);
    }

    @Override
    public void mostrarClaveIncompleto(String mensajeError) {
        textInputLayoutClave.setError(mensajeError);
    }

    @Override
    public void mostrarCamposIncompleto(String mensajeError) {
        textInputLayoutUsuario.setError(mensajeError);
        textInputLayoutClave.setError(mensajeError);
    }

    @Override
    public void nulearCamposIncompleto() {
        textInputLayoutUsuario.setError(null);
        textInputLayoutClave.setError(null);
    }

    @Override
    public void nulearUsuarioIncompleto() {
        textInputLayoutUsuario.setError(null);
    }

    @Override
    public void nulearClaveIncompleto() {
        textInputLayoutClave.setError(null);
    }

    @Override
    public void initSeleccionarInstituto(UsuarioUi usuario, String keyPeriodo) {
        progressDialog.dismiss();
        usuario.setKeyPeriodo(keyPeriodo);

        Intent intent = new Intent(this, InstitutoActivity.class);
        Bundle bundle = new Bundle();

        bundle.putParcelable("usuarioUi", Parcels.wrap(usuario));
        //  bundle.putString("keyPeriodo",keyPeriodo);
        //intent.putExtra("usuarioUi", usuario);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void initVistaPadre(String usuarioPadre, String ClavePadre) {
        Intent intent = new Intent(this, HijosActivity.class);
      /*  intent.putExtra("datousuario", usuario);
        intent.putExtra("datoclave", clave);*/
        startActivity(intent);

    }

    @Override
    public void initAutenticacion(final String usuario, final String clave) {
        progressDialog.setMessage("Cargando Sesión Espere....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(usuario, clave)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //   progressDialog.dismiss();
                        //if the task is successfull
                        if (task.isSuccessful()) {
                            //start the profile activity
                            presenter.onValidarAutenticacionInicio(usuario);
                            //initSeleccionarInstituto(usuario, clave);

                            //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                    }
                });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (firebaseAuth.getCurrentUser() != null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Cargando Sesión Espere....");
            presenter.onValidarAutenticacion(firebaseAuth, progressDialog);
            // presenter.onValidarAutenticacion(firebaseAuth.getCurrentUser().getEmail());
            progressDialog.show();


            //close this activity
            //opening profile activity
            // startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

            /*String email = firebaseAuth.getCurrentUser().getEmail();
            presenter.onValidarAutenticacion(email);*/

            // finish();
           /* String clave = "kikerojas12";
            initSeleccionarInstituto(email, clave);*/
        }
    }
}
