package com.systemvv.grupo.asitenciaapp.cursos.entidad;


import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.ColumnaCabeceraAsistencia;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class MotivosAsistenciaUi extends ColumnaCabeceraAsistencia {

    public static final int TIPO_ASISTENCIA_PUNTUAL = 1;
    public static final int TIPO_ASISTENCIA_TARDE = 2;
    public static final int TIPO_ASISTENCIA_TARDE_JUSTIFICADO = 3;
    public static final int TIPO_ASISTENCIA_FALTO = 4;

    public int tipoMotivo;
    public String justificacion;
    public List<AsistenciaUi> asistenciaUiList;


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

    /*public AlumnosUi getAlumnosUi() {
        return alumnosUi;
    }

    public void setAlumnosUi(AlumnosUi alumnosUi) {
        this.alumnosUi = alumnosUi;
    }*/
}
