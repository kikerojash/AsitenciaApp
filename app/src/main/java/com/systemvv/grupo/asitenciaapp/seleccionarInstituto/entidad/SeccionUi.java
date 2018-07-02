package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad;

import org.parceler.Parcel;

@Parcel
public class SeccionUi {
    private int grado;
    private String seccion;
    private InstitutoUi instituto;

    public SeccionUi() {
    }

    public SeccionUi(int grado, String seccion, InstitutoUi instituto) {
        this.grado = grado;
        this.seccion = seccion;
        this.instituto = instituto;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public InstitutoUi getInstituto() {
        return instituto;
    }

    public void setInstituto(InstitutoUi instituto) {
        this.instituto = instituto;
    }
}
