package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.useCase;

import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.dataSource.ReporteIncidenciaDataSource;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.dataSource.ReporteIncidenciaRepository;

import java.util.List;

public class ObtenerIncidenciaLista extends UseCase<ObtenerIncidenciaLista.RequestValues,ObtenerIncidenciaLista.ResponseValue>{

    private ReporteIncidenciaRepository reporteIncidenciaRepository;

    public ObtenerIncidenciaLista(ReporteIncidenciaRepository reporteIncidenciaRepository) {
        this.reporteIncidenciaRepository = reporteIncidenciaRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
     reporteIncidenciaRepository.onMostrarListaReporteIncidencia(requestValues.getCursos(), new ReporteIncidenciaDataSource.onCallBackResultado<List<Incidencias>>() {
         @Override
         public void onResultado(List<Incidencias> resultado) {
             getUseCaseCallback().onSuccess(new ResponseValue(resultado));
         }
     });
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<Incidencias> asistenciaList;

        public ResponseValue(List<Incidencias> asistenciaList) {
            this.asistenciaList = asistenciaList;
        }

        public List<Incidencias> getAsistenciaList() {
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
