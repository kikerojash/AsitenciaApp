package com.systemvv.grupo.asitenciaapp.padre.dataSource;

import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.padre.dataSource.remote.HijosRemote;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Instituto;

import java.util.List;

public class HijosRepository implements HijosDataSource {

    private HijosRemote hijosRemote;

    public HijosRepository(HijosRemote hijosRemote) {
        this.hijosRemote = hijosRemote;
    }

    @Override
    public void onObtenerMisHijos(UsuarioUi usuarioUi, onCallBackResultado<List<Hijos>> listonCallBackResultado) {
        hijosRemote.onObtenerMisHijos(usuarioUi, listonCallBackResultado);
    }

    @Override
    public void onObtenerInstituto(UsuarioUi usuarioUi, onCallBackResultado<Instituto> institutoonCallBackResultado) {
        hijosRemote.onObtenerInstituto(usuarioUi, institutoonCallBackResultado);
    }


}
