package com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.columnas;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.MotivoAsistencia;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColumnaTipoPresenteHolder extends AbstractViewHolder {
    @BindView(R.id.txtTitle)
    TextView textViewTitulo;
    @BindView(R.id.root)
    ConstraintLayout fondo;

    public ColumnaTipoPresenteHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(MotivoAsistencia motivosAsistenciaUi) {
        fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_green_600));
        textViewTitulo.setText("Presente");
        //fondo.getLayoutParams().width = ConstraintLayout.LayoutParams.MATCH_PARENT;
    }
}
