package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dataSource;

import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dataSource.remote.InstitutoRemote;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.entidad.InstitutoUi;

import java.util.List;

public class InstitutoRepository implements InstitutoDataSource {

    private InstitutoRemote institutoRemote;

    public InstitutoRepository(InstitutoRemote institutoRemote) {
        this.institutoRemote = institutoRemote;
    }

    @Override
    public void onObtenerListaInstituto(String keyUser, onCallBackResult<List<InstitutoUi>> listonCallBackResult) {
        institutoRemote.onObtenerListaInstituto(keyUser, listonCallBackResult);
    }
}
