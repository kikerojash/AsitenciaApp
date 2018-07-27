package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dataSource;

import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import java.util.List;

public interface InstitutoDataSource {

    interface onCallBackResult<T> {
        void onCalBackResult(T resultado);
    }

    void onObtenerListaInstituto(String keyUser, onCallBackResult<List<InstitutoUi>> listonCallBackResult);

}
