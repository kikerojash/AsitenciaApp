package com.systemvv.grupo.asitenciaapp.asistencia.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaRepository;
import com.systemvv.grupo.asitenciaapp.base.UseCase;

public class ValidarFechaRegistroAsistencia extends UseCase<ValidarFechaRegistroAsistencia.RequestValues,ValidarFechaRegistroAsistencia.ResponseValue>{

    private ControlAsistenciaRepository repository;

    public ValidarFechaRegistroAsistencia(ControlAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onValidarFechaRegistroAsistencia(requestValues.getFecha(), new ControlAsistenciaDataSource.ObjectCallbackSuccess<Boolean>() {
            @Override
            public void guardarAsistenciaGrupal(Boolean resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String fecha;

        public RequestValues(String fecha) {
            this.fecha = fecha;
        }

        public String getFecha() {
            return fecha;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private boolean aBoolean;

        public ResponseValue(boolean aBoolean) {
            this.aBoolean = aBoolean;
        }

        public boolean isaBoolean() {
            return aBoolean;
        }
    }
}
