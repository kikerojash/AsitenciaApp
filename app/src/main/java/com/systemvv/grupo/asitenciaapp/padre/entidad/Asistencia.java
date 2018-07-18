package com.systemvv.grupo.asitenciaapp.padre.entidad;

public class Asistencia {
    private String id;
    private String fecha;
    private int tipoAsistencia;
    private int conteo;

    public Asistencia() {
    }

    public Asistencia(String id, String fecha, int tipoAsistencia, int conteo) {
        this.id = id;
        this.fecha = fecha;
        this.tipoAsistencia = tipoAsistencia;
        this.conteo = conteo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(int tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }
}
