package com.systemvv.grupo.asitenciaapp.asistencia;

import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenter;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;

public interface AsistenciaPresenter extends BaseActivityPresenter<AsistenciaView> {

    void onClickAsistenciaCelda(AsistenciaUi asistenciaUi);

    void onClickAsistenciaTardeJustificado(AsistenciaUi asistenciaUi);

    void onClickAsistenciaTarde(AsistenciaUi asistenciaUi);

    void onClickAsistenciaPuntual(AsistenciaUi asistenciaUi);


    void onDesSelectAsistencia(AsistenciaUi asistenciaUi);

    void onSelectAsistencia(AsistenciaUi asistenciaUi);


}
