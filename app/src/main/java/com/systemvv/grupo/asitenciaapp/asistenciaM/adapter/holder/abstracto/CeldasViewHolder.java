package com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.holder.abstracto;

import android.view.View;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaCeldas;


public abstract class CeldasViewHolder<T extends AsistenciaCeldas> extends AbstractViewHolder {

    public CeldasViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T cell);

}
