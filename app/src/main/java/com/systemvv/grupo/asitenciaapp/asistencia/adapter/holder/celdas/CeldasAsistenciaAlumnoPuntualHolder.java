package com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.celdas;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Asistencia;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AsistenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CeldasAsistenciaAlumnoPuntualHolder extends AbstractViewHolder {
    @BindView(R.id.textViewValorNumerico)
    TextView textViewDatos;
    @BindView(R.id.root)
    ConstraintLayout fondo;
    Asistencia asistenciaUi;

    public CeldasAsistenciaAlumnoPuntualHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Asistencia asistencia) {
        this.asistenciaUi = asistencia;
        textViewDatos.setText("X");
        fondo.getLayoutParams().width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        if (asistencia.isPintar()) {
            fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_green_500));
        } else {
            fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
        }
    }

    /*public AsistenciaUi obtenerAsistencia(){
        return asistenciaUi;
    }*/
}
