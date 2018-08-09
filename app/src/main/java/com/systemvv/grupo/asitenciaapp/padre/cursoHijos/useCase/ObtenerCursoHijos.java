package com.systemvv.grupo.asitenciaapp.padre.cursoHijos.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.dataSource.CursoHijosDataSource;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.dataSource.CursoHijosRepository;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;

import java.util.List;

public class ObtenerCursoHijos extends UseCase<ObtenerCursoHijos.RequestValues, ObtenerCursoHijos.ResponseValue> {

    private CursoHijosRepository cursoHijosRepository;

    public ObtenerCursoHijos(CursoHijosRepository cursoHijosRepository) {
        this.cursoHijosRepository = cursoHijosRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        cursoHijosRepository.onObtenerListaCursosHijos(requestValues.getHijos(), new CursoHijosDataSource.onCallBackResultado<List<Cursos>>() {
            @Override
            public void onResult(List<Cursos> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        Hijos hijos;

        public RequestValues(Hijos hijos) {
            this.hijos = hijos;
        }

        public Hijos getHijos() {
            return hijos;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<Cursos> cursosList;

        public ResponseValue(List<Cursos> cursosList) {
            this.cursosList = cursosList;
        }

        public List<Cursos> getCursosList() {
            return cursosList;
        }
    }
}
