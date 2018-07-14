package com.systemvv.grupo.asitenciaapp.cursos.entidad;


import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;

import org.parceler.Parcel;

@Parcel
public class AsistenciaUi extends CeldasAsistencia {

    //  public enum TipoAsistencia {ASISTENCIA_PRESENTE, ASISTENCIA_TARDE, ASISTENCIA_JUSTIFICADA, ASISTENCIA_FALTO}
    public String tipoAsistencia;
    public int justificacion;
    public MotivosAsistenciaUi motivosAsistenciaUi;
    public AlumnosUi alumnosUi;
    public boolean pintar;
    //   private TipoAsistencia tipasistencia;


    public AsistenciaUi() {
    }

    public AsistenciaUi(String tipoAsistencia, int justificacion, MotivosAsistenciaUi motivosAsistenciaUi, AlumnosUi alumnosUi) {
        this.tipoAsistencia = tipoAsistencia;
        this.justificacion = justificacion;
        this.motivosAsistenciaUi = motivosAsistenciaUi;
        this.alumnosUi = alumnosUi;
    }

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

    public MotivosAsistenciaUi getMotivosAsistenciaUi() {
        return motivosAsistenciaUi;
    }

    public void setMotivosAsistenciaUi(MotivosAsistenciaUi motivosAsistenciaUi) {
        this.motivosAsistenciaUi = motivosAsistenciaUi;
    }

    public AlumnosUi getAlumnosUi() {
        return alumnosUi;
    }

    public void setAlumnosUi(AlumnosUi alumnosUi) {
        this.alumnosUi = alumnosUi;
    }

    public boolean isPintar() {
        return pintar;
    }

    public void setPintar(boolean pintar) {
        this.pintar = pintar;
    }

    /*public TipoAsistencia getTipasistencia() {
        return tipasistencia;
    }

    public void setTipasistencia(TipoAsistencia tipasistencia) {
        this.tipasistencia = tipasistencia;
    }*/
}
