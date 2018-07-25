package com.systemvv.grupo.asitenciaapp.asistencia.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaRepository;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;

import java.util.List;

public class GuardarAsistenciaLista extends UseCase<GuardarAsistenciaLista.ResquestValues,GuardarAsistenciaLista.ResponseValue>  {

    private ControlAsistenciaRepository repository;

    public GuardarAsistenciaLista(ControlAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(ResquestValues requestValues) {
        repository.onGuardarAsistenciaLista(requestValues.getAsistenciaUiList(), new ControlAsistenciaDataSource.ObjectCallbackSuccess<Boolean>() {
            @Override
            public void guardarAsistenciaGrupal(Boolean resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class ResquestValues implements UseCase.RequestValues{

        private List<AsistenciaUi> asistenciaUiList;

        public ResquestValues(List<AsistenciaUi> asistenciaUiList) {
            this.asistenciaUiList = asistenciaUiList;
        }

        public List<AsistenciaUi> getAsistenciaUiList() {
            return asistenciaUiList;
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
