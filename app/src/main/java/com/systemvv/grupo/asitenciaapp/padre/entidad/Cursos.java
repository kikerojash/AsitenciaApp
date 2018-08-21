package com.systemvv.grupo.asitenciaapp.padre.entidad;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Cursos {
     String id;
     String nombreCurso;
     String horario;
     String nombreProfesor;
     String fotoCurso;
     Hijos hijos;
     int countIncidencias;
     List<Incidencias> incidenciasList;

    public Cursos() {
    }

    public Cursos(String id, String nombreCurso, String horario, String nombreProfesor, String fotoCurso) {
        this.id = id;
        this.nombreCurso = nombreCurso;
        this.horario = horario;
        this.nombreProfesor = nombreProfesor;
        this.fotoCurso = fotoCurso;
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

    public String getFotoCurso() {
        return fotoCurso;
    }

    public void setFotoCurso(String fotoCurso) {
        this.fotoCurso = fotoCurso;
    }

    public Hijos getHijos() {
        return hijos;
    }

    public void setHijos(Hijos hijos) {
        this.hijos = hijos;
    }

    public List<Incidencias> getIncidenciasList() {
        return incidenciasList;
    }

    public void setIncidenciasList(List<Incidencias> incidenciasList) {
        this.incidenciasList = incidenciasList;
    }

    public int getCountIncidencias() {
        return countIncidencias;
    }

    public void setCountIncidencias(int countIncidencias) {
        this.countIncidencias = countIncidencias;
    }
}
