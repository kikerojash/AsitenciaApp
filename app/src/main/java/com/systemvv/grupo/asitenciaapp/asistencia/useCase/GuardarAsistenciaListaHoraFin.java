package com.systemvv.grupo.asitenciaapp.asistencia.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaRepository;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Asistencia;

import java.util.List;

public class GuardarAsistenciaListaHoraFin extends UseCase<GuardarAsistenciaListaHoraFin.RequestValues,GuardarAsistenciaListaHoraFin.ResponseValue>{

    private ControlAsistenciaRepository repository;

    public GuardarAsistenciaListaHoraFin(ControlAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onGuardarAsistenciaListaHoraFin(requestValues.getAsistenciaList(), new ControlAsistenciaDataSource.ObjectCallbackSuccess<Boolean>() {
            @Override
            public void guardarAsistenciaGrupal(Boolean resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private List<Asistencia> asistenciaList;

        public RequestValues(List<Asistencia> asistenciaList) {
            this.asistenciaList = asistenciaList;
        }

        public List<Asistencia> getAsistenciaList() {
            return asistenciaList;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        private boolean aBoolean;

        public ResponseValue(boolean aBoolean) {
            this.aBoolean = aBoolean;
        }

        public boolean isaBoolean() {
            return aBoolean;
        }
    }
}
