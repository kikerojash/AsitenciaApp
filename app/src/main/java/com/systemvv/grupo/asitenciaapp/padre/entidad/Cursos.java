package com.systemvv.grupo.asitenciaapp.padre.entidad;

import org.parceler.Parcel;

@Parcel
public class Cursos {
    private String id;
    private String nombreCurso;
    private String horario;
    private String nombreProfesor;

    public Cursos() {
    }

    public Cursos(String id, String nombreCurso, String horario, String nombreProfesor) {
        this.id = id;
        this.nombreCurso = nombreCurso;
        this.horario = horario;
        this.nombreProfesor = nombreProfesor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }
}
