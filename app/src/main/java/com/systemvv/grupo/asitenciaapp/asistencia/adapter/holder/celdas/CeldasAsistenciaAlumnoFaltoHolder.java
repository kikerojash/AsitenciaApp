package com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldasAsistenciaAlumnoFaltoHolder extends AbstractViewHolder {

    @BindView(R.id.textViewValorNumerico)
    TextView textViewDatos;
    @BindView(R.id.root)
    ConstraintLayout fondo;

    public CeldasAsistenciaAlumnoFaltoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Asistencia asistencia) {
        asistencia.setJustificacion(4);
        textViewDatos.setText("X");
        fondo.getLayoutParams().width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
        if (asistencia.isPintar()) {
            fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_red_500));
        } else {
            fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
        }
    }


}
