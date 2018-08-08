package com.systemvv.grupo.asitenciaapp.asistencia.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaRepository;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import java.util.List;

public class ValidarFechaRegistroAsistencia extends UseCase<ValidarFechaRegistroAsistencia.RequestValues,ValidarFechaRegistroAsistencia.ResponseValue>{

    private ControlAsistenciaRepository repository;

    public ValidarFechaRegistroAsistencia(ControlAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onValidarFechaRegistroAsistencia(requestValues.getFecha(),requestValues.getCursoUi(), new ControlAsistenciaDataSource.ObjectCallbackSuccessAsistencia<Boolean, List<String>>() {
            @Override
            public void guardarAsistenciaGrupal(Boolean resultado, List<String> kList, int tipoValidacionFecha) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado,kList,tipoValidacionFecha));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String fecha;
        private CursoUi cursoUi;

        public RequestValues(String fecha, CursoUi cursoUi) {
            this.fecha = fecha;
            this.cursoUi = cursoUi;
        }

        public String getFecha() {
            return fecha;
        }

        public CursoUi getCursoUi() {
            return cursoUi;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private boolean aBoolean;
        private List<String> stringList;
        private int tipoValidacionFecha;

        public ResponseValue(boolean aBoolean, List<String> stringList, int tipoValidacionFecha) {
            this.aBoolean = aBoolean;
            this.stringList = stringList;
            this.tipoValidacionFecha = tipoValidacionFecha;
        }

        public boolean isaBoolean() {
            return aBoolean;
        }

        public List<String> getStringList() {
            return stringList;
        }

        public int getTipoValidacionFecha() {
            return tipoValidacionFecha;
        }
    }
}
