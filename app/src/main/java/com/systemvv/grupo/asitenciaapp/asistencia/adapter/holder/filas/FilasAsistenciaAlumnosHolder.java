package com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.filas;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.AlumnosUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilasAsistenciaAlumnosHolder extends AbstractViewHolder {
    @BindView(R.id.txtTitle)
    TextView textViewNombre;
    @BindView(R.id.txtSubtittle)
    TextView textViewApellidos;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.root)
    ConstraintLayout fondo;


    public FilasAsistenciaAlumnosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(AlumnosUi alumnosUi) {
        textViewNombre.setText(alumnosUi.getNombre());
        textViewApellidos.setText(alumnosUi.getApellido());
        validacionEstadosaludAlumno(alumnosUi.getTipoPadecimiento());
        Glide.with(itemView.getContext()).load(alumnosUi.getFoto()).into(imgProfile);
    }

    private void validacionEstadosaludAlumno(int tipoPadecimiento) {
        switch (tipoPadecimiento) {
            case 0:
                fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
                break;
            case 1:
                fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_red_200));
                break;
            case 2:
                fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_red_200));
                break;
            default:
                fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
                break;
        }
    }
}
