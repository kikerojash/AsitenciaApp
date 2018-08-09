package com.systemvv.grupo.asitenciaapp.padre.dataSource;

import com.systemvv.grupo.asitenciaapp.login.dataSource.entidad.UsuarioUi;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Hijos;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Instituto;

import java.util.List;

public interface HijosDataSource {

    interface onCallBackResultado<T> {
        void onResult(T resultado);
    }

    void onObtenerMisHijos(UsuarioUi usuarioUi, onCallBackResultado<List<Hijos>> listonCallBackResultado);

    void onObtenerInstituto(UsuarioUi usuarioUi, onCallBackResultado<Instituto> institutoonCallBackResultado);


}
