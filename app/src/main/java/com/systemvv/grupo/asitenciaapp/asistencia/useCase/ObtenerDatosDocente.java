package com.systemvv.grupo.asitenciaapp.asistencia.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaRepository;
import com.systemvv.grupo.asitenciaapp.base.UseCase;

public class ObtenerDatosDocente extends UseCase<ObtenerDatosDocente.RequestValues,ObtenerDatosDocente.ResponseValue> {

    ControlAsistenciaRepository repository;

    public ObtenerDatosDocente(ControlAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onObtenerDatosDocente(requestValues.getKeyDocente(), new ControlAsistenciaDataSource.ObjectCallbackSuccessString<String, String>() {
            @Override
            public void guardarAsistenciaGrupal(String resultado, String resultado2) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado,resultado2));
            }
        });
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        String fotoDocente;
        String nombreDocente;

        public ResponseValue(String fotoDocente, String nombreDocente) {
            this.fotoDocente = fotoDocente;
            this.nombreDocente = nombreDocente;
        }

        public String getFotoDocente() {
            return fotoDocente;
        }

        public String getNombreDocente() {
            return nombreDocente;
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {
        String keyDocente;

        public RequestValues(String keyDocente) {
            this.keyDocente = keyDocente;
        }

        public String getKeyDocente() {
            return keyDocente;
        }
    }
}
