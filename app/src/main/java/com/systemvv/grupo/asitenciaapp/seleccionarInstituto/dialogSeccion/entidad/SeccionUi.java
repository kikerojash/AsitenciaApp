package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad;

import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import org.parceler.Parcel;

import java.util.Objects;

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
            int result=17;
            //result=31*result+age;
            result=31*result+(grado!=null ? grado.hashCode():0);
            return result;
        }

}
