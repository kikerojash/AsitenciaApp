package com.systemvv.grupo.asitenciaapp.asistencia.fireListener;

import java.util.List;

public interface FireCallBackList<T> {
    void onSuccess(T sucess, List<String> kList, int tipoValidacion);

}
