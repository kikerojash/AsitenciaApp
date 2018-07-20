package com.systemvv.grupo.asitenciaapp.asistencia.entidad;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.ColumnaCabeceraAsistencia;

import java.util.List;

public class MotivoAsistencia  extends ColumnaCabeceraAsistencia {

    public int tipoMotivo;
    public String justificacion;
    public List<Asistencia> asistenciaUiList;


    public MotivoAsistencia() {
    }

    public MotivoAsistencia(int tipoMotivo, String justificacion, List<Asistencia> asistenciaUiList) {
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

    public List<Asistencia> getAsistenciaUiList() {
        return asistenciaUiList;
    }

    public void setAsistenciaUiList(List<Asistencia> asistenciaUiList) {
        this.asistenciaUiList = asistenciaUiList;
    }
}
