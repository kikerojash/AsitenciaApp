package com.systemvv.grupo.asitenciaapp.padre.dataSource.remote;

import com.systemvv.grupo.asitenciaapp.fire.FireCallback;
import com.systemvv.grupo.asitenciaapp.fire.FireStore;
import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.padre.dataSource.HijosDataSource;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Instituto;

import java.util.List;

public class HijosRemote implements HijosDataSource {

    private FireStore fireStore;

    public HijosRemote(FireStore fireStore) {
        this.fireStore = fireStore;
    }

    @Override
    public void onObtenerMisHijos(UsuarioUi usuarioUi, final onCallBackResultado<List<Hijos>> listonCallBackResultado) {
        fireStore.onObtenerListaHijos(usuarioUi, new FireCallback<List<Hijos>>() {
            @Override
            public void onSuccess(List<Hijos> sucess) {
                listonCallBackResultado.onResult(sucess);
            }
        });
    }

    @Override
    public void onObtenerInstituto(UsuarioUi usuarioUi, final onCallBackResultado<Instituto> institutoonCallBackResultado) {
        fireStore.onObtenerInstituto(usuarioUi, new FireCallback<Instituto>() {
            @Override
            public void onSuccess(Instituto sucess) {
                institutoonCallBackResultado.onResult(sucess);
            }
        });
    }


}
