package com.systemvv.grupo.asitenciaapp.login.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.login.dataSource.LoginDataSource;
import com.systemvv.grupo.asitenciaapp.login.dataSource.LoginRepository;

public class ValidarPeriodo extends UseCase<ValidarPeriodo.RequestValues, ValidarPeriodo.ResponseValue> {

    private LoginRepository loginRepository;

    public ValidarPeriodo(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        loginRepository.onValidatePeriodo(new LoginDataSource.onCallBackResultado<String>() {
            @Override
            public void onCallBackResultado(String resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private String keyPeriodo;

        public ResponseValue(String keyPeriodo) {
            this.keyPeriodo = keyPeriodo;
        }

        public String getKeyPeriodo() {
            return keyPeriodo;
        }

        public void setKeyPeriodo(String keyPeriodo) {
            this.keyPeriodo = keyPeriodo;
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }
}
