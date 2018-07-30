package com.systemvv.grupo.asitenciaapp.cursos.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.cursos.dataSource.CursoDataSource;
import com.systemvv.grupo.asitenciaapp.cursos.dataSource.CursoRepository;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;

import java.util.List;

public class ObtenerCursoLista extends UseCase<ObtenerCursoLista.RequestValues,ObtenerCursoLista.ResponseValue>{

    private CursoRepository cursoRepository;

    public ObtenerCursoLista(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        cursoRepository.onMostrarListaCurso(requestValues.getSeccionUi(), new CursoDataSource.CallBackResultado<List<CursoUi>>() {
            @Override
            public void onCalBackResultado(List<CursoUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private SeccionUi seccionUi;

        public RequestValues(SeccionUi seccionUi) {
            this.seccionUi = seccionUi;
        }

        public SeccionUi getSeccionUi() {
            return seccionUi;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<CursoUi> cursoUiList;

        public ResponseValue(List<CursoUi> cursoUiList) {
            this.cursoUiList = cursoUiList;
        }

        public List<CursoUi> getCursoUiList() {
            return cursoUiList;
        }
    }
}


