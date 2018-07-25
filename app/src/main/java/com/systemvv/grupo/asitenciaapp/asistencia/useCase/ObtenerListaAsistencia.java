package com.systemvv.grupo.asitenciaapp.asistencia.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaRepository;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Asistencia;


import java.util.List;

public class ObtenerListaAsistencia extends UseCase<ObtenerListaAsistencia.RequestValues,ObtenerListaAsistencia.ResponseValue>{

    private ControlAsistenciaRepository repository;

    public ObtenerListaAsistencia(ControlAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onObtenerAsistenciaLista(requestValues.getFechaActual(), new ControlAsistenciaDataSource.ObjectCallbackSuccess<List<Asistencia>>() {
            @Override
            public void guardarAsistenciaGrupal(List<Asistencia> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String fechaActual;

        public RequestValues(String fechaActual) {
            this.fechaActual = fechaActual;
        }

        public String getFechaActual() {
            return fechaActual;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        private List<Asistencia> asistenciaList;

        public ResponseValue(List<Asistencia> asistenciaList) {
            this.asistenciaList = asistenciaList;
        }

        public List<Asistencia> getAsistenciaList() {
            return asistenciaList;
        }
    }

}
