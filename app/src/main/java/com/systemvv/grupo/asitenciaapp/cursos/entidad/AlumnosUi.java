package com.systemvv.grupo.asitenciaapp.cursos.entidad;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.FilaCabeceraAsistencia;

import org.parceler.Parcel;

@Parcel
//public class AlumnosUi extends AsistenciaColumnaCabecera {
public class AlumnosUi extends FilaCabeceraAsistencia {
    public int conteo;
    public String nombre;
    public String apellido;
    public String foto;
    public AsistenciaUi asistenciaUi;
    public MotivosAsistenciaUi motivosAsistenciaUi;
    public int tipoAsistencia;


    public AlumnosUi() {
    }

    public AlumnosUi(int conteo, String nombre, String apellido, String foto, AsistenciaUi asistenciaUi) {
        this.conteo = conteo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.foto = foto;
        this.asistenciaUi = asistenciaUi;
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

    public AsistenciaUi getAsistenciaUi() {
        return asistenciaUi;
    }

    public void setAsistenciaUi(AsistenciaUi asistenciaUi) {
        this.asistenciaUi = asistenciaUi;
    }

    public MotivosAsistenciaUi getMotivosAsistenciaUi() {
        return motivosAsistenciaUi;
    }

    public void setMotivosAsistenciaUi(MotivosAsistenciaUi motivosAsistenciaUi) {
        this.motivosAsistenciaUi = motivosAsistenciaUi;
    }

    public int getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(int tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }
}
