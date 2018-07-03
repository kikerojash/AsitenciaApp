package com.systemvv.grupo.asitenciaapp.asistenciaM.entidad;

import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaCeldas;

public class Asistencia extends AsistenciaCeldas {

    private String nombreAsistencia;
    private boolean aBoolean;
    private TipoAsistencia tipoAsistencia;

    public Asistencia() {
    }

    public Asistencia(String nombreAsistencia, boolean aBoolean, TipoAsistencia tipoAsistencia) {
        this.nombreAsistencia = nombreAsistencia;
        this.aBoolean = aBoolean;
        this.tipoAsistencia = tipoAsistencia;
    }

    public String getNombreAsistencia() {
        return nombreAsistencia;
    }

    public void setNombreAsistencia(String nombreAsistencia) {
        this.nombreAsistencia = nombreAsistencia;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public TipoAsistencia getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(TipoAsistencia tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }
}
