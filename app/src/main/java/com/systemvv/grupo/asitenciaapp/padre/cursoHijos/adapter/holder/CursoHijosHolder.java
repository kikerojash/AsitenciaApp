package com.systemvv.grupo.asitenciaapp.padre.cursoHijos.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CursoHijosHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView2)
    ImageView imageViewFotoCurso;
    @BindView(R.id.textViewNombreCurso)
    TextView textViewNombreCurso;
    @BindView(R.id.textViewGradoSeccion)
    TextView textViewGradoSeccion;
    @BindView(R.id.circleImageHijo)
    CircleImageView circleImageViewHijo;

    public CursoHijosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Cursos cursos) {
        textViewNombreCurso.setText(cursos.getNombreCurso());
        textViewGradoSeccion.setText("Grado: " + cursos.getHijos().getGrado() + " Sección: " + cursos.getHijos().getSeccion());
        Glide.with(itemView.getContext()).load(cursos.getFotoCurso()).into(imageViewFotoCurso);
        Glide.with(itemView.getContext()).load(cursos.getHijos().getFoto()).into(circleImageViewHijo);
    }
}
