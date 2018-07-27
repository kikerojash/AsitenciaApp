package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad;

import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import org.parceler.Parcel;

@Parcel
public class SeccionUi {

    private String keySeccion;
    private String grado;
    private String seccion;
    private InstitutoUi institutoUi;

    public SeccionUi() {
    }

    public String getKeySeccion() {
        return keySeccion;
    }

    public void setKeySeccion(String keySeccion) {
        this.keySeccion = keySeccion;
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

    public InstitutoUi getInstitutoUi() {
        return institutoUi;
    }

    public void setInstitutoUi(InstitutoUi institutoUi) {
        this.institutoUi = institutoUi;
    }
}
