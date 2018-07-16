package com.systemvv.grupo.asitenciaapp.padre.cursoHijos.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.systemvv.grupo.asitenciaapp.padre.entidad.Cursos;

import butterknife.ButterKnife;

public class CursoHijosHolder extends RecyclerView.ViewHolder {

    public CursoHijosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Cursos cursos) {
    }
}
