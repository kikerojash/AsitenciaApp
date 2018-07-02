package com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder;

import android.view.View;
import android.widget.TextView;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.abstracto.CeldasViewHolder;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColumnaAsistenciaTardeHolder extends CeldasViewHolder<MotivosAsistenciaUi> {
    @BindView(R.id.txtTitle)
    TextView textViewTitulo;

    public ColumnaAsistenciaTardeHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(MotivosAsistenciaUi cell) {
        textViewTitulo.setText("Tarde");
    }
}
