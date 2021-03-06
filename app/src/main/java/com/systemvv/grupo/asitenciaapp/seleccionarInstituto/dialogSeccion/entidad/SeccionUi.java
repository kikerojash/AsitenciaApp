package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad;

import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import org.parceler.Parcel;

@Parcel
public class SeccionUi {

    String keySeccion;
    String keyGrado;
    String grado;
    String seccion;
    InstitutoUi institutoUi;


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

    public String getKeyGrado() {
        return keyGrado;
    }

    public void setKeyGrado(String keyGrado) {
        this.keyGrado = keyGrado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeccionUi that = (SeccionUi) o;

        return grado.equals(that.grado) &&
                seccion.equals(that.seccion);
    }

    @Override
    public int hashCode() {
        int result = 17;
        //result=31*result+age;
        result = 31 * result + (grado != null ? grado.hashCode() : 0);
        return result;
    }


}
