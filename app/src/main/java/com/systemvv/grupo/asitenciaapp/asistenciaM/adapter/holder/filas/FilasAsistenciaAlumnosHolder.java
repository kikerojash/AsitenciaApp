package com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.holder.filas;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilasAsistenciaAlumnosHolder  extends AbstractViewHolder {
    @BindView(R.id.txtTitle)
    TextView textViewNombre;
    @BindView(R.id.txtSubtittle)
    TextView textViewApellidos;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;


    public FilasAsistenciaAlumnosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(AlumnosUi alumnosUi) {
        textViewNombre.setText(alumnosUi.getNombre());
        textViewApellidos.setText(alumnosUi.getApellido());
        Glide.with(itemView.getContext()).load(alumnosUi.getFoto()).into(imgProfile);
    }
}
