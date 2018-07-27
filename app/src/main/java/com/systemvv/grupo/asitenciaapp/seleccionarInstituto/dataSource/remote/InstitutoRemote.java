package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dataSource.remote;

import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dataSource.InstitutoDataSource;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import java.util.List;

public class InstitutoRemote implements InstitutoDataSource {

    private FireStore fireStore;

    public InstitutoRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }

    @Override
    public void onObtenerListaInstituto(String keyUser, final onCallBackResult<List<InstitutoUi>> listonCallBackResult) {
        fireStore.onObtenerListaInstituto(keyUser, new FireCallback<List<InstitutoUi>>() {
            @Override
            public void onSuccess(List<InstitutoUi> institutoUiList) {
                listonCallBackResult.onCalBackResult(institutoUiList);
            }
        });
    }

}
