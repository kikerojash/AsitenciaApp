package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.dataSource.SeccionDataSource;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.dataSource.SeccionRepository;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;

import java.util.List;

public class ObtenerListaSeccion extends UseCase<ObtenerListaSeccion.RequestValues, ObtenerListaSeccion.ResponseValue> {

    private SeccionRepository repository;

    public ObtenerListaSeccion(SeccionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onObtenerListaSeccion(requestValues.getKeyPeriodo(),requestValues.getKeyProfesor(), new SeccionDataSource.CallBackResult<List<SeccionUi>>() {
            @Override
            public void onCallResult(List<SeccionUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String keyPeriodo;
        private String keyProfesor;

        public RequestValues(String keyPeriodo, String keyProfesor) {
            this.keyPeriodo = keyPeriodo;
            this.keyProfesor = keyProfesor;
        }

        public String getKeyPeriodo() {
            return keyPeriodo;
        }

        public String getKeyProfesor() {
            return keyProfesor;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<SeccionUi> seccionUiList;

        public ResponseValue(List<SeccionUi> seccionUiList) {
            this.seccionUiList = seccionUiList;
        }

        public List<SeccionUi> getSeccionUiList() {
            return seccionUiList;
        }
    }
}
