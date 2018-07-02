package com.systemvv.grupo.asitenciaapp.test.adapter.holder.abstracto;

import android.view.View;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.systemvv.grupo.asitenciaapp.test.adapter.estructura.AsistenciaFilas;
import com.systemvv.grupo.asitenciaapp.test.adapter.holder.AsistenciaFilasHolder;

public abstract class FilasViewHolder<T extends AsistenciaFilas> extends AbstractViewHolder {

    public FilasViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T rowHeader);
}

