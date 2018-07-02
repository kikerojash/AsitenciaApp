package com.systemvv.grupo.asitenciaapp.test.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.MotivosAsistenciaUi;
import com.systemvv.grupo.asitenciaapp.test.TipoAsistencia;
import com.systemvv.grupo.asitenciaapp.test.adapter.holder.abstracto.FilasViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsistenciaFilasHolder extends FilasViewHolder<TipoAsistencia> {

    @BindView(R.id.txtTitle)
    TextView textViewTitulo;

    public AsistenciaFilasHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(TipoAsistencia cell) {
        textViewTitulo.setText(cell.getMotivoAsistencia().name()+"");
    }
}