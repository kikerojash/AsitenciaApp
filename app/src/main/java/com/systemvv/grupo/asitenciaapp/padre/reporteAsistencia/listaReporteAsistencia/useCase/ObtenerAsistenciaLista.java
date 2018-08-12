package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.dataSource.ReporteAsistenciaDataSource;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteAsistencia.dataSource.ReporteAsistenciaRepository;

import java.util.List;

public class ObtenerAsistenciaLista extends UseCase<ObtenerAsistenciaLista.RequestValues, ObtenerAsistenciaLista.ResponseValue> {

    private ReporteAsistenciaRepository reporteAsistenciaRepository;

    public ObtenerAsistenciaLista(ReporteAsistenciaRepository reporteAsistenciaRepository) {
        this.reporteAsistenciaRepository = reporteAsistenciaRepository;
    }

    @Override
    protected void executeUseCase(final RequestValues requestValues) {
        reporteAsistenciaRepository.onMostrarListaReporteAsistencia(requestValues.getCursos(), new ReporteAsistenciaDataSource.onCallBackResultado<List<Asistencia>>() {
            @Override
            public void onResultado(List<Asistencia> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<Asistencia> asistenciaList;

        public ResponseValue(List<Asistencia> asistenciaList) {
            this.asistenciaList = asistenciaList;
        }

        public List<Asistencia> getAsistenciaList() {
            return asistenciaList;
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {
        Cursos cursos;

        public RequestValues(Cursos cursos) {
            this.cursos = cursos;
        }

        public Cursos getCursos() {
            return cursos;
        }
    }
}
