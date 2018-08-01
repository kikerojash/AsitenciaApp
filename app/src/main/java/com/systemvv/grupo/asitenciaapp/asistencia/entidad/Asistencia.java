package com.systemvv.grupo.asitenciaapp.asistencia.entidad;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;

public class Asistencia extends CeldasAsistencia {

    public enum TipoAsistencia {ASISTENCIA_TARDE, ASISTENCIA_TARDE_JUSTIFICADA, ASISTENCIA_FALTO, ASISTENCIA_FALTO_JUSTIFICADA}

    public String tipoAsistencia;
    public int justificacion;
    public MotivoAsistencia motivosAsistenciaUi;
    public Alumnos alumnosUi;
    public boolean pintar;
    public TipoAsistencia tipasistencia;


    public Asistencia() {
    }

   /* public Asistencia(String tipoAsistencia, int justificacion, MotivoAsistencia motivosAsistenciaUi, Alumnos alumnosUi) {
        this.tipoAsistencia = tipoAsistencia;
        this.justificacion = justificacion;
        this.motivosAsistenciaUi = motivosAsistenciaUi;
        this.alumnosUi = alumnosUi;
        this.tipasistencia = TipoAsistencia.ASISTENCIA_TARDE;

    }*/

    public String getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(String tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public int getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(int justificacion) {
        this.justificacion = justificacion;
    }

    public MotivoAsistencia getMotivosAsistenciaUi() {
        return motivosAsistenciaUi;
    }

    public void setMotivosAsistenciaUi(MotivoAsistencia motivosAsistenciaUi) {
        this.motivosAsistenciaUi = motivosAsistenciaUi;
    }

    public Alumnos getAlumnosUi() {
        return alumnosUi;
    }

    public void setAlumnosUi(Alumnos alumnosUi) {
        this.alumnosUi = alumnosUi;
    }

    public boolean isPintar() {
        return pintar;
    }

    public void setPintar(boolean pintar) {
        this.pintar = pintar;
    }

    public TipoAsistencia getTipasistencia() {
        return tipasistencia;
    }

    public void setTipasistencia(TipoAsistencia tipasistencia) {
        this.tipasistencia = tipasistencia;
    }
}
