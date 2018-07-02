package com.systemvv.grupo.asitenciaapp.test;

import com.systemvv.grupo.asitenciaapp.test.adapter.estructura.AsistenciaCeldas;

public class TipoAsistencia extends AsistenciaCeldas {
    public enum MotivoAsistencia {TIPO_ASISTENCIA_PRESENTE, TTIPO_ASISTENCIA_TARDE, TIPO_ASISTENCIA_FALTO}
    private MotivoAsistencia motivoAsistencia;
    private String nombre;

    public TipoAsistencia() {
    }

    public TipoAsistencia(MotivoAsistencia motivoAsistencia, String nombre) {
        this.motivoAsistencia = motivoAsistencia;
        this.nombre = nombre;
    }

    public MotivoAsistencia getMotivoAsistencia() {
        return motivoAsistencia;
    }

    public void setMotivoAsistencia(MotivoAsistencia motivoAsistencia) {
        this.motivoAsistencia = motivoAsistencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
