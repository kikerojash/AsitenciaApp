package com.systemvv.grupo.asitenciaapp.padre.cursoHijos.adapter.holder;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.systemvv.grupo.asitenciaapp.R;
import com.systemvv.grupo.asitenciaapp.padre.cursoHijos.listener.CursoHijosListener;
import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CursoHijosHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.imageView2)
    ImageView imageViewFotoCurso;
    @BindView(R.id.textViewNombreCurso)
    TextView textViewNombreCurso;
    @BindView(R.id.textViewGradoSeccion)
    TextView textViewGradoSeccion;
    @BindView(R.id.circleImageHijo)
    CircleImageView circleImageViewHijo;
    @BindView(R.id.cardViewItem)
    ConstraintLayout constraintLayout;
    @BindView(R.id.textView12)
    TextView textViewContadorIncidencia;
    CursoHijosListener listener;
    Cursos cursos;


    public CursoHijosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        constraintLayout.setOnClickListener(this);
    }

    public void bind(Cursos cursos, CursoHijosListener listener) {
        this.cursos = cursos;
        this.listener = listener;
        textViewNombreCurso.setText(cursos.getNombreCurso());
        textViewGradoSeccion.setText("Grado: " + cursos.getHijos().getGrado() + " Sección: " + cursos.getHijos().getSeccion());
        if(cursos.getIncidenciasList()==null){
            textViewContadorIncidencia.setText("0");
        }else {
            textViewContadorIncidencia.setText(cursos.getIncidenciasList().size()+"");
        }

        Glide.with(itemView.getContext()).load(cursos.getFotoCurso()).into(imageViewFotoCurso);
        Glide.with(itemView.getContext()).load(cursos.getHijos().getFoto()).into(circleImageViewHijo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardViewItem:
                listener.onItemClickCursoHijos(cursos);
                break;
        }
    }
}
