package com.systemvv.grupo.asitenciaapp.cursos.entidad;


import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;

import org.parceler.Parcel;

@Parcel
public class AsistenciaUi  {

    public enum TipoAsistencia {ASISTENCIA_TARDE, ASISTENCIA_TARDE_JUSTIFICADA, ASISTENCIA_FALTO, ASISTENCIA_FALTO_JUSTIFICADA}

     String tipoAsistencia;
     int justificacion;
     MotivosAsistenciaUi motivosAsistenciaUi;
     AlumnosUi alumnosUi;
     boolean pintar;
     TipoAsistencia tipasistencia;

     String fecha;
     String horaInicioCurso;
     String horaFinCurso;


    public AsistenciaUi() {
    }

    public AsistenciaUi(String tipoAsistencia, int justificacion, MotivosAsistenciaUi motivosAsistenciaUi, AlumnosUi alumnosUi) {
        this.tipoAsistencia = tipoAsistencia;
        this.justificacion = justificacion;
        this.motivosAsistenciaUi = motivosAsistenciaUi;
        this.alumnosUi = alumnosUi;
        this.tipasistencia = TipoAsistencia.ASISTENCIA_TARDE;

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

    public TipoAsistencia getTipasistencia() {
        return tipasistencia;
    }

    public void setTipasistencia(TipoAsistencia tipasistencia) {
        this.tipasistencia = tipasistencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicioCurso() {
        return horaInicioCurso;
    }

    public void setHoraInicioCurso(String horaInicioCurso) {
        this.horaInicioCurso = horaInicioCurso;
    }

    public String getHoraFinCurso() {
        return horaFinCurso;
    }

    public void setHoraFinCurso(String horaFinCurso) {
        this.horaFinCurso = horaFinCurso;
    }
}
