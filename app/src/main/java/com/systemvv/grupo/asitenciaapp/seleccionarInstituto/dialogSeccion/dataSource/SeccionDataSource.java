package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.dataSource;

import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;

import java.util.List;

public interface SeccionDataSource {

    interface CallBackResult<T>{
        void onCallResult(T resultado);
    }

    void onObtenerListaSeccion(String keyPeriodo,String keyProfesor, CallBackResult<List<SeccionUi>> listCallBackResult);
}
