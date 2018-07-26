package com.systemvv.grupo.asitenciaapp.asistencia.dialog;


import android.os.Bundle;

public interface IncidenciaPresenter extends com.systemvv.grupo.asitenciaapp.base.BasePresenter {

    void onBtnAceptar(String mensajeIncidencia);

    void onSeleccionSpinnerIncidencia(String nivelIncidencia);

    void onExtras(Bundle bundle);

}
