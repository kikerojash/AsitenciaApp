package com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.holder.columnas;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColumnaTipoTardeHolder extends AbstractViewHolder {
    @BindView(R.id.txtTitle)
    TextView textViewTitulo;
    @BindView(R.id.root)
    ConstraintLayout fondo;
    public ColumnaTipoTardeHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(MotivosAsistenciaUi motivosAsistenciaUi) {
        textViewTitulo.setText("Tardanza");
        fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_yellow_900));
    }
}
