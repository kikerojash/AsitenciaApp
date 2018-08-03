package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.dataSource.remote;

import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.dataSource.SeccionDataSource;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;

import java.util.List;

public class SeccionRemote implements SeccionDataSource {

    private FireStore fireStore;

    public SeccionRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }

    @Override
    public void onObtenerListaSeccion(String keyPeriodo,String keyProfesor, final CallBackResult<List<SeccionUi>> listCallBackResult) {
        fireStore.onObtenerListaSeccionGrado(keyPeriodo,keyProfesor, new FireCallback<List<SeccionUi>>() {
            @Override
            public void onSuccess(List<SeccionUi> sucess) {
                listCallBackResult.onCallResult(sucess);
            }
        });
    }
}
