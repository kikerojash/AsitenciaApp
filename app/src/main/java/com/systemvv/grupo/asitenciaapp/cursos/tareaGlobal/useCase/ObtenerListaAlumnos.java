package com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.useCase;


import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.dataSource.TareaGlobalDataSource;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.dataSource.TareaGlobalRepository;
import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.entidad.TareasGlobales;

import java.util.List;

public class ObtenerListaAlumnos extends UseCase<ObtenerListaAlumnos.RequestValues, ObtenerListaAlumnos.ResponseValue> {

    TareaGlobalRepository tareaGlobalRepository;

    public ObtenerListaAlumnos(TareaGlobalRepository tareaGlobalRepository) {
        this.tareaGlobalRepository = tareaGlobalRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        tareaGlobalRepository.onObtenerListaAlumnos(requestValues.getCursoUi(), new TareaGlobalDataSource.CallBackResultad<List<TareasGlobales>>() {
            @Override
            public void onCallResultado(List<TareasGlobales> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        CursoUi cursoUi;

        public RequestValues(CursoUi cursoUi) {
            this.cursoUi = cursoUi;
        }

        public CursoUi getCursoUi() {
            return cursoUi;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<TareasGlobales> tareasGlobalesList;

        public ResponseValue(List<TareasGlobales> tareasGlobalesList) {
            this.tareasGlobalesList = tareasGlobalesList;
        }

        public List<TareasGlobales> getTareasGlobalesList() {
            return tareasGlobalesList;
        }
    }
}
