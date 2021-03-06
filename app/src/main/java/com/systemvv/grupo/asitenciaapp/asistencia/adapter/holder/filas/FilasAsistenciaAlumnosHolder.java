package com.systemvv.grupo.asitenciaapp.asistencia.adapter.holder.filas;

import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.asistencia.entidad.Alumnos;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilasAsistenciaAlumnosHolder extends AbstractViewHolder {

    public static final String TAG = FilasAsistenciaAlumnosHolder.class.getSimpleName();
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

    public void bind(Alumnos alumnosUi) {
        textViewNombre.setText(alumnosUi.getNombre());
        textViewApellidos.setText(alumnosUi.getApellido());
        validacionEstadosaludAlumno(alumnosUi.getPadecimiento());
        Glide.with(itemView.getContext()).load(alumnosUi.getFoto()).into(imgProfile);
    }

    private void validacionEstadosaludAlumno(String tipoPadecimiento) {
        Log.d(TAG, "validacionEstadosaludAlumno " + tipoPadecimiento);
        if (tipoPadecimiento == null || tipoPadecimiento.equals("null")) {
            fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
        } else if (tipoPadecimiento.length() > 0) {
            fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_red_200));
        } else {
            fondo.setBackgroundColor(itemView.getResources().getColor(R.color.md_white_1000));
        }
    }
}
