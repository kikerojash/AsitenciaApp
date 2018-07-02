package com.systemvv.grupo.asitenciaapp.test.adapter.holder.abstracto;

import android.view.View;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;
import com.systemvv.grupo.asitenciaapp.test.adapter.estructura.AsistenciaColumna;

public abstract class ColumnaViewHolder <T extends AsistenciaColumna> extends AbstractSorterViewHolder {

    public ColumnaViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T columnHeader);
}
