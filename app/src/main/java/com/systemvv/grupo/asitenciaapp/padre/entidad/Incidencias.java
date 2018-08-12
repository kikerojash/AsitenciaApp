package com.systemvv.grupo.asitenciaapp.padre.entidad;

import org.parceler.Parcel;

@Parcel
public class Incidencias {
     int conteo;
     String nombreIncidencias;
     String fecha;
     String hora;
     int gradoIncidencia;
     String tipoIncidencia;

    public Incidencias() {
    }

    public Incidencias(int conteo, String nombreIncidencias, String fecha, String hora, int gradoIncidencia) {
        this.conteo = conteo;
        this.nombreIncidencias = nombreIncidencias;
        this.fecha = fecha;
        this.hora = hora;
        this.gradoIncidencia = gradoIncidencia;
    }

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }

    public String getNombreIncidencias() {
        return nombreIncidencias;
    }

    public void setNombreIncidencias(String nombreIncidencias) {
        this.nombreIncidencias = nombreIncidencias;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getGradoIncidencia() {
        return gradoIncidencia;
    }

    public void setGradoIncidencia(int gradoIncidencia) {
        this.gradoIncidencia = gradoIncidencia;
    }

    public String getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(String tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }
}
