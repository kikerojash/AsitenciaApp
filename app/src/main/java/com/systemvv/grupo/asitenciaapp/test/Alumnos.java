package com.systemvv.grupo.asitenciaapp.test;

import com.systemvv.grupo.asitenciaapp.test.adapter.estructura.AsistenciaColumna;

public class Alumnos extends AsistenciaColumna{
    private String nombre;
    private String apellido;
    private String foto;

    public Alumnos() {
    }

    public Alumnos(String nombre, String apellido, String foto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.foto = foto;
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
}
