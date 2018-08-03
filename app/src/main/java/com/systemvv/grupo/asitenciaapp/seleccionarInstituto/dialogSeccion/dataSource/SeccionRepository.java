package com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.dataSource;

import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.dataSource.remote.SeccionRemote;
import com.systemvv.grupo.asitenciaapp.seleccionarInstituto.dialogSeccion.entidad.SeccionUi;

import java.util.List;

public class SeccionRepository implements SeccionDataSource {

    private SeccionRemote remote;

    public SeccionRepository(SeccionRemote remote) {
        this.remote = remote;
    }

    @Override
    public void onObtenerListaSeccion(String keyDocente, String keyProfesor, CallBackResult<List<SeccionUi>> listCallBackResult) {
        remote.onObtenerListaSeccion(keyDocente, keyProfesor, listCallBackResult);
    }
}
