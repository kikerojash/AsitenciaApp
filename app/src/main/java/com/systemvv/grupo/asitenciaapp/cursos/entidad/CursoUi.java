package com.systemvv.grupo.asitenciaapp.cursos.entidad;

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
    InstitutoUi institutoUi;
    List<AlumnosUi> alumnosUiList;
    List<MotivosAsistenciaUi> motivosAsistenciaUiList;
    List<AsistenciaUi> asistenciaUis;

    public CursoUi() {
    }

    public CursoUi(String nombre, String seccionSelected, int gradoSelected, String foto, InstitutoUi institutoUi, List<AlumnosUi> alumnosUiList, List<MotivosAsistenciaUi> motivosAsistenciaUiList, List<AsistenciaUi> asistenciaUis) {
        this.nombre = nombre;
        this.seccionSelected = seccionSelected;
        this.gradoSelected = gradoSelected;
        this.foto = foto;
        this.institutoUi = institutoUi;
        this.alumnosUiList = alumnosUiList;
        this.motivosAsistenciaUiList = motivosAsistenciaUiList;
        this.asistenciaUis = asistenciaUis;
    }
    //    public CursoUi(String nombre, String seccionSelected, int gradoSelected, String foto, InstitutoUi institutoUi, List<AlumnosUi> alumnosUiList) {
//        this.nombre = nombre;
//        this.seccionSelected = seccionSelected;
//        this.gradoSelected = gradoSelected;
//        this.foto = foto;
//        this.institutoUi = institutoUi;
//        this.alumnosUiList = alumnosUiList;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getSeccionSelected() {
//        return seccionSelected;
//    }
//
//    public void setSeccionSelected(String seccionSelected) {
//        this.seccionSelected = seccionSelected;
//    }
//
//    public int getGradoSelected() {
//        return gradoSelected;
//    }
//
//    public void setGradoSelected(int gradoSelected) {
//        this.gradoSelected = gradoSelected;
//    }
//
//    public String getFoto() {
//        return foto;
//    }
//
//    public void setFoto(String foto) {
//        this.foto = foto;
//    }
//
//    public InstitutoUi getInstitutoUi() {
//        return institutoUi;
//    }
//
//    public void setInstitutoUi(InstitutoUi institutoUi) {
//        this.institutoUi = institutoUi;
//    }
//
//    public List<AlumnosUi> getAlumnosUiList() {
//        return alumnosUiList;
//    }
//
//    public void setAlumnosUiList(List<AlumnosUi> alumnosUiList) {
//        this.alumnosUiList = alumnosUiList;
//    }


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

    public List<AlumnosUi> getAlumnosUiList() {
        return alumnosUiList;
    }

    public void setAlumnosUiList(List<AlumnosUi> alumnosUiList) {
        this.alumnosUiList = alumnosUiList;
    }

    public List<MotivosAsistenciaUi> getMotivosAsistenciaUiList() {
        return motivosAsistenciaUiList;
    }

    public void setMotivosAsistenciaUiList(List<MotivosAsistenciaUi> motivosAsistenciaUiList) {
        this.motivosAsistenciaUiList = motivosAsistenciaUiList;
    }

    public List<AsistenciaUi> getAsistenciaUis() {
        return asistenciaUis;
    }

    public void setAsistenciaUis(List<AsistenciaUi> asistenciaUis) {
        this.asistenciaUis = asistenciaUis;
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

    public void setConteoAlumnos(String conteoAlumnos) {
        this.conteoAlumnos = conteoAlumnos;
    }
}
