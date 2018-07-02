package com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaCelda;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.abstracto.CeldasViewHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.listener.AsitenciaCeldasListener;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldasAsistenciaFaltoHolder extends CeldasViewHolder<AsistenciaUi> {
    @BindView(R.id.textViewValorNumerico)
    TextView textViewDatos;
    @BindView(R.id.root)
    ConstraintLayout fondo;
    AsistenciaUi asistenciaUi;

    public CeldasAsistenciaFaltoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(AsistenciaUi cell) {
        this.asistenciaUi = cell;
        textViewDatos.setText(cell.getJustificacion() + "");
        if (cell.isPintar()){
            fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_red_A100));
        }else {
            fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
        }
    }



    public AsistenciaUi obtenerAsistenciaUi() {
        return asistenciaUi;
    }
}
