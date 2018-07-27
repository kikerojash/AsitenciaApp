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
        repository.onObtenerListaSeccion(requestValues.getKeyDocente(), new SeccionDataSource.CallBackResult<List<SeccionUi>>() {
            @Override
            public void onCallResult(List<SeccionUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String keyDocente;

        public RequestValues(String keyDocente) {
            this.keyDocente = keyDocente;
        }

        public String getKeyDocente() {
            return keyDocente;
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
