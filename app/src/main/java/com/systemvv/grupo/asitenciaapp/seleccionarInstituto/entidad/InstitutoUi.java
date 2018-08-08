package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad;


import org.parceler.Parcel;

@Parcel
public class InstitutoUi {
    String keyInstituto;
    String nombre;
    String image;
    String cede;
    String direccion;
    String keyUsuario;
    String keyPeriodo;


    public InstitutoUi() {
    }

    public InstitutoUi(String nombre, String image, String cede, String direccion) {
        this.nombre = nombre;
        this.image = image;
        this.cede = cede;
        this.direccion = direccion;
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

    public String getKeyUsuario() {
        return keyUsuario;
    }

    public void setKeyUsuario(String keyUsuario) {
        this.keyUsuario = keyUsuario;
    }

    public String getKeyPeriodo() {
        return keyPeriodo;
    }

    public void setKeyPeriodo(String keyPeriodo) {
        this.keyPeriodo = keyPeriodo;
    }

    public String getKeyInstituto() {
        return keyInstituto;
    }

    public void setKeyInstituto(String keyInstituto) {
        this.keyInstituto = keyInstituto;
    }


}
