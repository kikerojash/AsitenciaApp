package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Tareas;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.dataSource.ReporteTareasDataSource;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReportesTareas.dataSource.ReporteTareasRepository;

import java.util.List;

public class ObtenerTareasLista extends UseCase<ObtenerTareasLista.RequestValues,ObtenerTareasLista.ResponseValues> {

    ReporteTareasRepository reporteTareasRepository;

    public ObtenerTareasLista(ReporteTareasRepository reporteTareasRepository) {
        this.reporteTareasRepository = reporteTareasRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        reporteTareasRepository.onObtenerListaTareas(requestValues.getCursos(), new ReporteTareasDataSource.CallbackResultado<List<Tareas>>() {
            @Override
            public void onCallBackResultado(List<Tareas> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValues(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        Cursos cursos;

        public RequestValues(Cursos cursos) {
            this.cursos = cursos;
        }

        public Cursos getCursos() {
            return cursos;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValue{
        List<Tareas> tareasList;

        public ResponseValues(List<Tareas> tareasList) {
            this.tareasList = tareasList;
        }

        public List<Tareas> getTareasList() {
            return tareasList;
        }
    }
}
