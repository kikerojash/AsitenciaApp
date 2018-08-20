package com.systemvv.grupo.asitenciaapp.asistencia.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.ControlAsistenciaRepository;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;

import java.util.List;

public class ObtenerListaAlumnos extends UseCase<ObtenerListaAlumnos.RequestValues,ObtenerListaAlumnos.ResponseValue> {

    ControlAsistenciaRepository repository;

    public ObtenerListaAlumnos(ControlAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
       repository.onObtenerAlumnosLista(requestValues.getCursoUi(), new ControlAsistenciaDataSource.ObjectCallbackSuccess<List<Alumnos>>() {
           @Override
           public void guardarAsistenciaGrupal(List<Alumnos> resultado) {
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
        List<Alumnos> alumnosList;

        public ResponseValue(List<Alumnos> alumnosList) {
            this.alumnosList = alumnosList;
        }

        public List<Alumnos> getAlumnosList() {
            return alumnosList;
        }
    }
}
