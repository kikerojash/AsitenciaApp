package com.systemvv.grupo.asitenciaapp.cursos.adapter.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.cursos.entidad.CursoUi;
import com.systemvv.grupo.asitenciaapp.cursos.listener.CursoListener;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CursoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.txtNombreCurso)
    TextView textViewNombreCurso;
    @BindView(R.id.fondo)
    ImageView imageViewFondo;
    @BindView(R.id.textTitulo)
    TextView textViewInstituto;
    @BindView(R.id.txtGrado)
    TextView textViewGradoSeccion;
    @BindView(R.id.txtSalon)
    TextView textViewAlumnosContar;
    @BindView(R.id.cardviewCurso)
    CardView cardViewCursos;
    @BindView(R.id.textViewHoraInicioFin)
    TextView textViewHoraInicioFin;
    CursoListener listener;
    CursoUi cursoUi;

    public CursoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardViewCursos.setOnClickListener(this);
    }

    public void bind(CursoUi cursoUi, CursoListener listener) {
        this.listener = listener;
        this.cursoUi = cursoUi;
        if (cursoUi.getInstitutoUi().getNombre() == null) return;
        textViewNombreCurso.setText(cursoUi.getNombre());
        textViewInstituto.setText(cursoUi.getInstitutoUi().getNombre());
        textViewGradoSeccion.setText("Grado : " + cursoUi.getGradoSelected() + " Sección : " + cursoUi.getSeccionSelected());
        textViewHoraInicioFin.setText(cursoUi.getHoraInicio()+ "-"+cursoUi.getHoraFin());
        textViewAlumnosContar.setText(cursoUi.getConteoAlumnos());

        Glide.with(itemView.getContext()).load(cursoUi.getFoto()).into(imageViewFondo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardviewCurso:
                listener.onClickCursoItem(cursoUi);
                break;
        }
    }
}
