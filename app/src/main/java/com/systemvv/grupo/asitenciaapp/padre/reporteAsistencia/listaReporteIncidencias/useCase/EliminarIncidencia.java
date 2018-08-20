package com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.useCase;

import com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.IncidenciaDataSource;
import com.systemvv.grupo.asitenciaapp.asistencia.dialog.dataSource.IncidenciaRepository;
import com.systemvv.grupo.asitenciaapp.base.UseCase;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Incidencias;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.dataSource.ReporteIncidenciaDataSource;
import com.systemvv.grupo.asitenciaapp.padre.reporteAsistencia.listaReporteIncidencias.dataSource.ReporteIncidenciaRepository;

public class EliminarIncidencia extends UseCase<EliminarIncidencia.RequestValues,EliminarIncidencia.ResponseValue>{

    ReporteIncidenciaRepository reporteIncidenciaRepository;

    public EliminarIncidencia(ReporteIncidenciaRepository reporteIncidenciaRepository) {
        this.reporteIncidenciaRepository = reporteIncidenciaRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        reporteIncidenciaRepository.onEliminarIncidencia(requestValues.getIncidencias(), new ReporteIncidenciaDataSource.onCallBackResultado<Boolean>() {
            @Override
            public void onResultado(Boolean resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        boolean aBoolean;

        public ResponseValue(boolean aBoolean) {
            this.aBoolean = aBoolean;
        }

        public boolean isaBoolean() {
            return aBoolean;
        }
    }

    public static final class RequestValues implements UseCase.RequestValues{
        Incidencias incidencias;

        public RequestValues(Incidencias incidencias) {
            this.incidencias = incidencias;
        }

        public Incidencias getIncidencias() {
            return incidencias;
        }

    }
}
