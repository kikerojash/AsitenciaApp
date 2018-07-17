package com.systemvv.grupo.asitenciaapp.padre.entidad;

import org.parceler.Parcel;

import java.util.List;
@Parcel
public class Hijos {
    private String id;
    private String nombre;
    private String apellido;
    private String edad;
    private String nombreInstituto;
    private String grado;
    private String seccion;
    private String foto;
    private List<Cursos> cursosList;

    public Hijos() {
    }

    public Hijos(String id, String nombre, String apellido, String edad, String nombreInstituto, String grado, String seccion, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.nombreInstituto = nombreInstituto;
        this.grado = grado;
        this.seccion = seccion;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getNombreInstituto() {
        return nombreInstituto;
    }

    public void setNombreInstituto(String nombreInstituto) {
        this.nombreInstituto = nombreInstituto;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Cursos> getCursosList() {
        return cursosList;
    }

    public void setCursosList(List<Cursos> cursosList) {
        this.cursosList = cursosList;
    }
}