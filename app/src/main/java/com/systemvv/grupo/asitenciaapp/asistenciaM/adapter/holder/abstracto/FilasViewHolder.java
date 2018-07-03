package com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.holder.abstracto;

import android.view.View;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.asistenciaM.adapter.estructura.AsistenciaFilas;

public abstract class FilasViewHolder<T extends AsistenciaFilas> extends AbstractViewHolder {

    public FilasViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T rowHeader);
}

