package com.systemvv.grupo.asitenciaapp.asistencia.entidad;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;

public class Asistencia extends CeldasAsistencia {

    //public enum TipoAsistencia {ASISTENCIA_TARDE, ASISTENCIA_TARDE_JUSTIFICADA, ASISTENCIA_FALTO, ASISTENCIA_FALTO_JUSTIFICADA}

    public String tipoAsistencia;
    public int justificacion;
    public MotivoAsistencia motivosAsistenciaUi;
    public Alumnos alumnosUi;
    public boolean pintar;
    //public TipoAsistencia tipasistencia;
    String fecha;
    String horaInicioCurso;
    String horaFinCurso;


    public Asistencia() {
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
