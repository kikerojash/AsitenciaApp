package com.systemvv.grupo.asitenciaapp.login.dataSource.entidad;

import org.parceler.Parcel;

@Parcel
public class UsuarioUi {

    String keyUser;
    String per_nombre;
    String per_apellido;
    String tip_usuario;
    String tip_usuario_nombre;
    String usu_email;

    public UsuarioUi() {
    }

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
    }

    public String getPer_nombre() {
        return per_nombre;
    }

    public void setPer_nombre(String per_nombre) {
        this.per_nombre = per_nombre;
    }

    public String getPer_apellido() {
        return per_apellido;
    }

    public void setPer_apellido(String per_apellido) {
        this.per_apellido = per_apellido;
    }

    public String getTip_usuario() {
        return tip_usuario;
    }

    public void setTip_usuario(String tip_usuario) {
        this.tip_usuario = tip_usuario;
    }

    public String getTip_usuario_nombre() {
        return tip_usuario_nombre;
    }

    public void setTip_usuario_nombre(String tip_usuario_nombre) {
        this.tip_usuario_nombre = tip_usuario_nombre;
    }

    public String getUsu_email() {
        return usu_email;
    }

    public void setUsu_email(String usu_email) {
        this.usu_email = usu_email;
    }
}
