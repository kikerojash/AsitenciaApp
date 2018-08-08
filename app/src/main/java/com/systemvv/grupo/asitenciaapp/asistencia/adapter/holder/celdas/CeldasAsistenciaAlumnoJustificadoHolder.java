package com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldasAsistenciaAlumnoJustificadoHolder extends AbstractViewHolder {

    @BindView(R.id.textViewValorNumerico)
    TextView textViewDatos;
    @BindView(R.id.root)
    ConstraintLayout fondo;

    public CeldasAsistenciaAlumnoJustificadoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}
