package com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.abstracto;

import android.view.View;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaCelda;

public abstract class CeldasViewHolder<T extends AsistenciaCelda> extends AbstractViewHolder {

    public CeldasViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T cell);
}