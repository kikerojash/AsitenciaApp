package com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.columnas;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColumnaTipoFaltoHolder extends AbstractViewHolder {
    @BindView(R.id.txtTitle)
    TextView textViewTitulo;
    @BindView(R.id.root)
    ConstraintLayout fondo;

    public ColumnaTipoFaltoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(MotivosAsistenciaUi motivosAsistenciaUi) {
        textViewTitulo.setText("Ausente");
        fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_red_A400));
      //  fondo.getLayoutParams().width = ConstraintLayout.LayoutParams.MATCH_PARENT;
    }
}
