package com.systemvv.grupo.asitenciaapp.test.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;
import com.systemvv.grupo.asitenciaapp.test.Alumnos;
import com.systemvv.grupo.asitenciaapp.test.adapter.holder.abstracto.ColumnaViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsistenciaAlumnosHolder extends ColumnaViewHolder<Alumnos> {
    @BindView(R.id.txtTitle)
    TextView textViewNombre;
    @BindView(R.id.txtSubtittle)
    TextView textViewApellidos;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;

    public AsistenciaAlumnosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Alumnos columnHeader) {
        textViewNombre.setText(columnHeader.getNombre());
        textViewApellidos.setText(columnHeader.getApellido());
    }
}
