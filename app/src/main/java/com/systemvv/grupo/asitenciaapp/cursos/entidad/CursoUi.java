package com.systemvv.grupo.asitenciaapp.cursos.entidad;

import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class CursoUi {
    String keyCurso;
    String nombre;
    String horaInicio;
    String horaFin;
    String seccionSelected;
    int gradoSelected;
    String conteoAlumnos;
    String foto;
    String keyGrado;
    String KeySeccion;
    InstitutoUi institutoUi;
    SeccionUi seccionUi;


    public CursoUi() {
    }

    public String getKeyCurso() {
        return keyCurso;
    }

    public void setKeyCurso(String keyCurso) {
        this.keyCurso = keyCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSeccionSelected() {
        return seccionSelected;
    }

    public void setSeccionSelected(String seccionSelected) {
        this.seccionSelected = seccionSelected;
    }

    public int getGradoSelected() {
        return gradoSelected;
    }

    public void setGradoSelected(int gradoSelected) {
        this.gradoSelected = gradoSelected;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public InstitutoUi getInstitutoUi() {
        return institutoUi;
    }

    public void setInstitutoUi(InstitutoUi institutoUi) {
        this.institutoUi = institutoUi;
    }

    public SeccionUi getSeccionUi() {
        return seccionUi;
    }

    public void setSeccionUi(SeccionUi seccionUi) {
        this.seccionUi = seccionUi;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getConteoAlumnos() {
        return conteoAlumnos;
    }

    public String getKeyGrado() {
        return keyGrado;
    }

    public void setKeyGrado(String keyGrado) {
        this.keyGrado = keyGrado;
    }

    public String getKeySeccion() {
        return KeySeccion;
    }

    public void setKeySeccion(String keySeccion) {
        KeySeccion = keySeccion;
    }

    public void setConteoAlumnos(String conteoAlumnos) {
        this.conteoAlumnos = conteoAlumnos;
    }
}
