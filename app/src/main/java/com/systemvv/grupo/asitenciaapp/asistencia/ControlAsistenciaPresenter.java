package com.systemvv.grupo.asitenciaapp.asistencia;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.systemvv.grupo.asitenciaapp.asistencia.adapter.estructura.CeldasAsistencia;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.base.activity.BaseActivityPresenter;

import java.util.List;

public interface ControlAsistenciaPresenter extends BaseActivityPresenter<ControlAsistenciaView> {

    void onClickColumnaCabecera(@NonNull RecyclerView.ViewHolder holder, List<CeldasAsistencia> clickColumnaList);

    void onClickCeldas(@NonNull RecyclerView.ViewHolder holder, Asistencia asistenciaUi, List<CeldasAsistencia> clickCeldasList);

    void onGuardarEntrada();

    void onGuardarSalida();

    void onClickAccionBar(int tipoResultado);


}
