package com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal;


import android.os.Bundle;

import com.systemvv.grupo.asitenciaapp.cursos.tareaGlobal.view.TareaGlobalView;

public interface TareaGlobalPresenter extends com.systemvv.grupo.asitenciaapp.base.BasePresenter<TareaGlobalView> {

    void onExtras(Bundle bundle);

    void onClickEnviarTarea(String tarea);
}
