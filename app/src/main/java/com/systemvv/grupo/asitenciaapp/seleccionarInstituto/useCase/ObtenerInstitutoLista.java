package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dataSource.InstitutoDataSource;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dataSource.InstitutoRepository;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import java.util.List;

public class ObtenerInstitutoLista extends UseCase<ObtenerInstitutoLista.RequestValues, ObtenerInstitutoLista.ResponseValue> {

    private InstitutoRepository repository;

    public ObtenerInstitutoLista(InstitutoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onObtenerListaInstituto(requestValues.getKeyUsuario(), new InstitutoDataSource.onCallBackResult<List<InstitutoUi>>() {
            @Override
            public void onCalBackResult(List<InstitutoUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String keyUsuario;

        public RequestValues(String keyUsuario) {
            this.keyUsuario = keyUsuario;
        }

        public String getKeyUsuario() {
            return keyUsuario;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<InstitutoUi> institutoUiList;

        public ResponseValue(List<InstitutoUi> institutoUiList) {
            this.institutoUiList = institutoUiList;
        }

        public List<InstitutoUi> getInstitutoUiList() {
            return institutoUiList;
        }
    }
}
