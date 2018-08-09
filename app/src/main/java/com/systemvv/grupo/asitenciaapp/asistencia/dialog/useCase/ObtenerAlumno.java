package com.systemvv.grupo.asitenciaapp.asistencia.dialog.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.IncidenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.IncidenciaRepository;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.base.UseCase;

public class ObtenerAlumno extends UseCase<ObtenerAlumno.RequestValues, ObtenerAlumno.ResponseValue> {

    private IncidenciaRepository repository;

    public ObtenerAlumno(IncidenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onObtenerAlumno(requestValues.getKeyUser(), new IncidenciaDataSource.onCallBackResult<Alumnos>() {
            @Override
            public void onResponse(Alumnos response) {
                getUseCaseCallback().onSuccess(new ResponseValue(response));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String keyUser;

        public RequestValues(String keyUser) {
            this.keyUser = keyUser;
        }

        public String getKeyUser() {
            return keyUser;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private Alumnos alumnos;

        public ResponseValue(Alumnos alumnos) {
            this.alumnos = alumnos;
        }

        public Alumnos getAlumnos() {
            return alumnos;
        }
    }
}
