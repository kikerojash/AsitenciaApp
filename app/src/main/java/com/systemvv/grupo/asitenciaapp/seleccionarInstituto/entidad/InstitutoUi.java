package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class InstitutoUi {
     String nombre;
     String image;
     String cede;
     String direccion;
     String keyUsuario;
     List<SeccionUi> seccionList;

    public InstitutoUi() {
    }

    public InstitutoUi(String nombre, String image, String cede, String direccion, List<SeccionUi> seccionList) {
        this.nombre = nombre;
        this.image = image;
        this.cede = cede;
        this.direccion = direccion;
        this.seccionList = seccionList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCede() {
        return cede;
    }

    public void setCede(String cede) {
        this.cede = cede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<SeccionUi> getSeccionList() {
        return seccionList;
    }

    public void setSeccionList(List<SeccionUi> seccionList) {
        this.seccionList = seccionList;
    }

    public String getKeyUsuario() {
        return keyUsuario;
    }

    public void setKeyUsuario(String keyUsuario) {
        this.keyUsuario = keyUsuario;
    }
}
