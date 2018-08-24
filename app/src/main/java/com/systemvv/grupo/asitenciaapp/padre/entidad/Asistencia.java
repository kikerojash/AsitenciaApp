package com.systemvv.grupo.asitenciaapp.padre.entidad;

public class Asistencia {
    private String id;
    private String fecha;
    private int tipoAsistencia;
    private int conteo;
    private String inicioRegistroHora;
    private String finRegistroHora;
    private String tipASistencia;
    private long timeStamp;

    public Asistencia() {
    }

    public Asistencia(String id, String fecha, int tipoAsistencia, int conteo, String inicioRegistroHora, String finRegistroHora) {
        this.id = id;
        this.fecha = fecha;
        this.tipoAsistencia = tipoAsistencia;
        this.conteo = conteo;
        this.inicioRegistroHora = inicioRegistroHora;
        this.finRegistroHora = finRegistroHora;
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

    public String getInicioRegistroHora() {
        return inicioRegistroHora;
    }

    public void setInicioRegistroHora(String inicioRegistroHora) {
        this.inicioRegistroHora = inicioRegistroHora;
    }

    public String getFinRegistroHora() {
        return finRegistroHora;
    }

    public void setFinRegistroHora(String finRegistroHora) {
        this.finRegistroHora = finRegistroHora;
    }

    public String getTipASistencia() {
        return tipASistencia;
    }

    public void setTipASistencia(String tipASistencia) {
        this.tipASistencia = tipASistencia;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
