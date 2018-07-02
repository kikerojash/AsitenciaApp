package com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.abstracto.FilasViewHolder;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilasAsistenciaAlumnosHolder extends FilasViewHolder<AlumnosUi> {
    @BindView(R.id.txtTitle)
    TextView textViewNombre;
    @BindView(R.id.txtSubtittle)
    TextView textViewApellidos;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;

    public FilasAsistenciaAlumnosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(AlumnosUi columnHeader) {
        textViewNombre.setText(columnHeader.getNombre());
        textViewApellidos.setText(columnHeader.getApellido());
    }
}
