package com.systemvv.grupo.asitenciaapp.asistencia.entidad;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.FilaCabeceraAsistencia;

public class Alumnos extends FilaCabeceraAsistencia {

    public int conteo;
    public String nombre;
    public String apellido;
    public String foto;

    public int tipoAsistencia;
    public int tipoPadecimiento;


    public Alumnos() {
    }

    public Alumnos(int conteo, String nombre, String apellido, String foto) {
        this.conteo = conteo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.foto = foto;

    }

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(int tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public int getTipoPadecimiento() {
        return tipoPadecimiento;
    }

    public void setTipoPadecimiento(int tipoPadecimiento) {
        this.tipoPadecimiento = tipoPadecimiento;
    }
}
