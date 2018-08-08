package com.systemvv.grupo.asitenciaapp.asistencia.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaRepository;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import java.util.List;

public class GuardarAsistenciaLista extends UseCase<GuardarAsistenciaLista.ResquestValues,GuardarAsistenciaLista.ResponseValue>  {

    private ControlAsistenciaRepository repository;

    public GuardarAsistenciaLista(ControlAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(ResquestValues requestValues) {
        repository.onGuardarAsistenciaLista(requestValues.getAsistenciaUiList(),requestValues.getCursoUi(), new ControlAsistenciaDataSource.ObjectCallbackSuccess<Boolean>() {
            @Override
            public void guardarAsistenciaGrupal(Boolean resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class ResquestValues implements UseCase.RequestValues{

        private List<Asistencia> asistenciaUiList;
        private CursoUi cursoUi;

        public ResquestValues(List<Asistencia> asistenciaUiList, CursoUi cursoUi) {
            this.asistenciaUiList = asistenciaUiList;
            this.cursoUi = cursoUi;
        }

        public List<Asistencia> getAsistenciaUiList() {
            return asistenciaUiList;
        }

        public CursoUi getCursoUi() {
            return cursoUi;
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
