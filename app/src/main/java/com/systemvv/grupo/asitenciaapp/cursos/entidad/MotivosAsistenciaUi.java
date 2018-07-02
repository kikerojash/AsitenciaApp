package com.systemvv.grupo.asitenciaapp.cursos.entidad;

import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaCelda;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class MotivosAsistenciaUi extends AsistenciaCelda {

    public static final int TIPO_ASISTENCIA_PUNTUAL = 1;
    public static final int TIPO_ASISTENCIA_TARDE = 2;
    public static final int TIPO_ASISTENCIA_TARDE_JUSTIFICADO = 3;
    public static final int TIPO_ASISTENCIA_FALTO = 4;
    private int tipoMotivo;
    private String justificacion;
    private List<AsistenciaUi> asistenciaUiList;

    public MotivosAsistenciaUi() {
    }

    public MotivosAsistenciaUi(int tipoMotivo, String justificacion, List<AsistenciaUi> asistenciaUiList) {
        this.tipoMotivo = tipoMotivo;
        this.justificacion = justificacion;
        this.asistenciaUiList = asistenciaUiList;
    }

    public int getTipoMotivo() {
        return tipoMotivo;
    }

    public void setTipoMotivo(int tipoMotivo) {
        this.tipoMotivo = tipoMotivo;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public List<AsistenciaUi> getAsistenciaUiList() {
        return asistenciaUiList;
    }

    public void setAsistenciaUiList(List<AsistenciaUi> asistenciaUiList) {
        this.asistenciaUiList = asistenciaUiList;
    }
}
