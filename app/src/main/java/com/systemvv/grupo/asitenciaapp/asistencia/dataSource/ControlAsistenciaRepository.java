package com.systemvv.grupo.asitenciaapp.asistencia.dataSource;

import com.systemvv.grupo.asitenciaapp.asistencia.dataSource.remote.ControlAsistenciaRemote;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;
import com.systemvv.grupo.asitenciaapp.fire.entidad.Asistencia;

import java.util.List;

public class ControlAsistenciaRepository implements ControlAsistenciaDataSource {

    private ControlAsistenciaRemote asistenciaRemote;

    public ControlAsistenciaRepository(ControlAsistenciaRemote asistenciaRemote) {
        this.asistenciaRemote = asistenciaRemote;
    }

    @Override
    public void onGuardarAsistenciaLista(List<AsistenciaUi> asistenciaUiList, ObjectCallbackSuccess<Boolean> callbackSuccess) {
        asistenciaRemote.onGuardarAsistenciaLista(asistenciaUiList,callbackSuccess);
    }

    @Override
    public void onGuardarAsistenciaListaHoraFin(List<Asistencia> asistenciaList, ObjectCallbackSuccess<Boolean> callbackSuccess) {
        asistenciaRemote.onGuardarAsistenciaListaHoraFin(asistenciaList,callbackSuccess);
    }

    @Override
    public void onObtenerAsistenciaLista(String fecha, ObjectCallbackSuccess<List<Asistencia>> listObjectCallbackSuccess) {
        asistenciaRemote.onObtenerAsistenciaLista(fecha,listObjectCallbackSuccess);
    }

    @Override
    public void onValidarFechaRegistroAsistencia(String fecha, ObjectCallbackSuccess<Boolean> callbackSuccess) {
        asistenciaRemote.onValidarFechaRegistroAsistencia(fecha,callbackSuccess);
    }
}
