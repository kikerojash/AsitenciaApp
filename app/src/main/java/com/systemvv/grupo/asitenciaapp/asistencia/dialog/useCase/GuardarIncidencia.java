package com.systemvv.grupo.asitenciaapp.asistencia.dialog.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.IncidenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.IncidenciaRepository;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.entidadui.IncidenciaUi;
import com.systemvv.grupo.asitenciaapp.base.UseCase;

public class GuardarIncidencia extends UseCase<GuardarIncidencia.RequestValues, GuardarIncidencia.ResponseValue> {

    private IncidenciaRepository repository;

    public GuardarIncidencia(IncidenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
      repository.onGuardarIncidencia(requestValues.getIncidenciaUi(), new IncidenciaDataSource.onCallBackResult<Boolean>() {
          @Override
          public void onResponse(Boolean response) {
              getUseCaseCallback().onSuccess(new ResponseValue(response));
          }
      });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private IncidenciaUi incidenciaUi;

        public RequestValues(IncidenciaUi incidenciaUi) {
            this.incidenciaUi = incidenciaUi;
        }

        public IncidenciaUi getIncidenciaUi() {
            return incidenciaUi;
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
