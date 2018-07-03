package com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.holder.abstracto.ColumnaViewHolder;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;
import com.systemvv.grupo.asitenciaapp.test.Alumnos;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsistenciaAlumnosHolder extends ColumnaViewHolder<AlumnosUi> {
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
    public void bind(AlumnosUi columnHeader) {
        textViewNombre.setText(columnHeader.getNombre());
        textViewApellidos.setText(columnHeader.getApellido());
        Glide.with(itemView.getContext()).load(columnHeader.getFoto()).into(imgProfile);
    }
}
