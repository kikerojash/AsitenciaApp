package com.systemvv.grupo.asitenciaapp.login.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.login.dataSource.LoginDataSource;
import com.systemvv.grupo.asitenciaapp.login.dataSource.LoginRepository;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;

public class ValidarRolUsuario extends UseCase<ValidarRolUsuario.RequestValues, ValidarRolUsuario.ResponseValue> {

    private LoginRepository loginRepository;

    public ValidarRolUsuario(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    protected void executeUseCase(final RequestValues requestValues) {
        loginRepository.onValidarTipoUsuario(requestValues.getEmail(), new LoginDataSource.onCallBackResultado<UsuarioUi>() {
            @Override
            public void onCallBackResultado(UsuarioUi resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String email;

        public RequestValues(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        UsuarioUi usuarioUi;

        public ResponseValue(UsuarioUi usuarioUi) {
            this.usuarioUi = usuarioUi;
        }

        public UsuarioUi getUsuarioUi() {
            return usuarioUi;
        }
    }
}
