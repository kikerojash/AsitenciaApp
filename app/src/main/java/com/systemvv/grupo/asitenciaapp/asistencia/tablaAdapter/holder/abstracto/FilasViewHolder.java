package com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.holder.abstracto;

import android.view.View;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaColumnaCabecera;
import com.systemvv.grupo.asitenciaapp.asistencia.tablaAdapter.estructura.AsistenciaFilaCabecera;

//AsistenciaColumnaCabecera
public abstract class FilasViewHolder <T extends AsistenciaColumnaCabecera> extends AbstractSorterViewHolder {

    public FilasViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T columnHeader);

}
