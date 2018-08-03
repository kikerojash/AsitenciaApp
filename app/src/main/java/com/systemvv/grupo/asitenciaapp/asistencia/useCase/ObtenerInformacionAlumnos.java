package com.systemvv.grupo.asitenciaapp.asistencia.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaRepository;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.base.UseCase;

import java.util.List;

public class ObtenerInformacionAlumnos extends UseCase<ObtenerInformacionAlumnos.RequestValues, ObtenerInformacionAlumnos.ResponseValue> {

    private ControlAsistenciaRepository repository;

    public ObtenerInformacionAlumnos(ControlAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onObtenerInformacionAlumnos(requestValues.getAlumnosList(), new ControlAsistenciaDataSource.ObjectCallbackSuccess<Alumnos>() {
            @Override
            public void guardarAsistenciaGrupal(Alumnos resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private List<Alumnos> alumnosList;

        public RequestValues(List<Alumnos> alumnosList) {
            this.alumnosList = alumnosList;
        }

        public List<Alumnos> getAlumnosList() {
            return alumnosList;
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
